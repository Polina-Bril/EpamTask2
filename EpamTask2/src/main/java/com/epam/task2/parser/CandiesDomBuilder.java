package com.epam.task2.parser;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.epam.task2.entity.Candy;
import com.epam.task2.entity.ChocoType;
import com.epam.task2.entity.ChocolateCandy;
import com.epam.task2.entity.IcicleCandy;
import com.epam.task2.entity.Ingredients;
import com.epam.task2.entity.Value;
import com.epam.task2.exception.CandyException;
import com.epam.task2.handler.CandiesXmlTag;

public class CandiesDomBuilder extends AbstractCandyBuilder {
	public static Logger logger = LogManager.getLogger();
	private DocumentBuilder docBuilder;

	public CandiesDomBuilder() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			docBuilder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			logger.log(Level.ERROR, " parser configuration error");
		}
	}

	public CandiesDomBuilder(Set<Candy> candies) {
		super(candies);
	}

	@Override
	public void buildSetCandies(String filename) throws CandyException {
		Document doc;
		try {
			doc = docBuilder.parse(filename);
			Element root = doc.getDocumentElement();
			NodeList icicleCandiesList = root.getElementsByTagName(CandiesXmlTag.ICICLE_CANDY.getTag());
			NodeList chocolateCandiesList = root.getElementsByTagName(CandiesXmlTag.CHOCOLATE_CANDY.getTag());
			for (int i = 0; i < icicleCandiesList.getLength(); i++) {
				Element candyElement = (Element) icicleCandiesList.item(i);
				Candy candy = buildCandy(candyElement, CandiesXmlTag.ICICLE_CANDY.getTag());
				candies.add(candy);
			}
			for (int i = 0; i < chocolateCandiesList.getLength(); i++) {
				Element candyElement = (Element) chocolateCandiesList.item(i);
				Candy candy = buildCandy(candyElement, CandiesXmlTag.CHOCOLATE_CANDY.getTag());
				candies.add(candy);
			}
			logger.log(Level.INFO, "parsing result: " + getCandies());
		} catch (SAXException | IOException e) {
			throw new CandyException("parsing error", e);
		}
	}

	private Candy buildCandy(Element candyElement, String tagName) {
		Candy candy = null;
		switch (tagName) {
		case "icicle-candy":
			candy = new IcicleCandy();
			break;
		case "chocolate-candy":
			candy = new ChocolateCandy();
			break;
		}
		NodeList valueList = candyElement.getElementsByTagName(CandiesXmlTag.VALUE.getTag());
		for (int i = 0; i < valueList.getLength(); i++) {
			Element valueElement = (Element) valueList.item(i);
			Value value = buildValue(valueElement);
			candy.setValue(value);
		}
		NodeList ingredientsList = candyElement.getElementsByTagName(CandiesXmlTag.INGREDIENTS.getTag());
		for (int i = 0; i < ingredientsList.getLength(); i++) {
			Element ingredientsElement = (Element) ingredientsList.item(i);
			Ingredients ingredients = buildIngredients(ingredientsElement);
			candy.setIngredients(ingredients);
		}
		candy.setName(getElementTextContent(candyElement, CandiesXmlTag.NAME_ID.getTag()));
		LocalDateTime expiringDate = LocalDateTime
				.parse(getElementTextContent(candyElement, CandiesXmlTag.EXPIRED_DATE.getTag()));
		candy.setExpiredDate(expiringDate);
		candy.setProduction(getElementTextContent(candyElement, CandiesXmlTag.PRODUCTION.getTag()));
		Integer energy = Integer.parseInt(getElementTextContent(candyElement, CandiesXmlTag.ENERGY.getTag()));
		candy.setEnergy(energy);
		if (candy instanceof IcicleCandy) {
			Boolean isStuffed = Boolean
					.parseBoolean(getElementTextContent(candyElement, CandiesXmlTag.IS_STUFFED.getTag()));
			((IcicleCandy) candy).setStuffed(isStuffed);
		} else {
			String chocoType = getElementTextContent(candyElement, CandiesXmlTag.CHOCO_TYPE.getTag());
			for (ChocoType th : ChocoType.values()) {
				if (th.getType().equals(chocoType)) {
					((ChocolateCandy) candy).setChocoType(th);
				}
			}
		}
		return candy;
	}

	private Value buildValue(Element valueElement) {
		Value value = new Value();
		Integer proteins = Integer.parseInt(getElementTextContent(valueElement, CandiesXmlTag.PROTEINS.getTag()));
		value.setProteins(proteins);
		Integer fats = Integer.parseInt(getElementTextContent(valueElement, CandiesXmlTag.FATS.getTag()));
		value.setProteins(fats);
		Integer carbohydrates = Integer
				.parseInt(getElementTextContent(valueElement, CandiesXmlTag.CARBOHYDRATES.getTag()));
		value.setProteins(carbohydrates);
		return value;
	}

	private Ingredients buildIngredients(Element ingredientsElement) {
		Ingredients ingredients = new Ingredients();
		Integer water = Integer.parseInt(getElementTextContent(ingredientsElement, CandiesXmlTag.WATER.getTag()));
		ingredients.setWater(water);
		Integer sugar = Integer.parseInt(getElementTextContent(ingredientsElement, CandiesXmlTag.SUGAR.getTag()));
		ingredients.setSugar(sugar);
		if (ingredientsElement.getElementsByTagName(CandiesXmlTag.FRUCTOSE.getTag()) != null) {
			Integer fructose = Integer
					.parseInt(getElementTextContent(ingredientsElement, CandiesXmlTag.FRUCTOSE.getTag()));
			ingredients.setFructose(fructose);
		}
		if (ingredientsElement.getElementsByTagName(CandiesXmlTag.VANILIN.getTag()) != null) {
			Integer vanilin = Integer
					.parseInt(getElementTextContent(ingredientsElement, CandiesXmlTag.VANILIN.getTag()));
			ingredients.setVanillin(vanilin);
		}
		return ingredients;
	}

	private static String getElementTextContent(Element element, String elementName) {
		NodeList nList = element.getElementsByTagName(elementName);
		Node node = nList.item(0);
		String text = node.getTextContent();
		return text;
	}
}