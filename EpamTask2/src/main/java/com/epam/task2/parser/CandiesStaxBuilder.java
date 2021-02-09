package com.epam.task2.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Set;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.task2.entity.Candy;
import com.epam.task2.entity.ChocoType;
import com.epam.task2.entity.ChocolateCandy;
import com.epam.task2.entity.IcicleCandy;
import com.epam.task2.entity.Ingredients;
import com.epam.task2.entity.Value;
import com.epam.task2.exception.CandyException;
import com.epam.task2.handler.CandiesXmlTag;

public class CandiesStaxBuilder extends AbstractCandyBuilder {
	public static Logger logger = LogManager.getLogger();
	private XMLInputFactory inputFactory;
	public final static String HYPHEN = "-";
	public final static String UNDERSCORE = "_";

	public CandiesStaxBuilder() {
		inputFactory = XMLInputFactory.newInstance();
	}

	public CandiesStaxBuilder(Set<Candy> candies) {
		super(candies);
		inputFactory = XMLInputFactory.newInstance();
	}

	@Override
	public void buildSetCandies(String filename) throws CandyException {
		XMLStreamReader reader;
		String name;
		try (FileInputStream inputStream = new FileInputStream(new File(filename))) {
			reader = inputFactory.createXMLStreamReader(inputStream);
			while (reader.hasNext()) {
				int type = reader.next();
				if (type == XMLStreamConstants.START_ELEMENT) {
					name = reader.getLocalName();
					if (name.equals(CandiesXmlTag.ICICLE_CANDY.getTag())
							|| name.equals(CandiesXmlTag.CHOCOLATE_CANDY.getTag())) {
						Candy candy = buildCandy(reader, name);
						candies.add(candy);
					}
				}
			}
			logger.log(Level.INFO, "parsing result: " + getCandies());
		} catch (XMLStreamException | FileNotFoundException e) {
			throw new CandyException("parsing error", e);
		} catch (IOException e) {
			throw new CandyException(filename + "is not found", e);
		}
	}

	private Candy buildCandy(XMLStreamReader reader, String tagName) throws CandyException, XMLStreamException {
		Candy candy = null;
		switch (tagName) {
		case "icicle-candy":
			candy = new IcicleCandy();
			break;
		case "chocolate-candy":
			candy = new ChocolateCandy();
			break;
		}
		int type;
		String name;
		while (reader.hasNext()) {
			type = reader.next();
			switch (type) {
			case XMLStreamConstants.START_ELEMENT:
				name = reader.getLocalName();
				switch (CandiesXmlTag.valueOf(name.replace(HYPHEN, UNDERSCORE).toUpperCase())) {
				case NAME_ID:
					candy.setName(getXMLText(reader));
					break;
				case ENERGY:
					Integer energy = Integer.parseInt(getXMLText(reader));
					candy.setEnergy(energy);
					break;
				case EXPIRED_DATE:
					LocalDateTime expiringDate = LocalDateTime.parse(getXMLText(reader));
					candy.setExpiredDate(expiringDate);
					break;
				case PRODUCTION:
					candy.setProduction(getXMLText(reader));
					break;
				case VALUE:
					Value value = buildValue(reader);
					candy.setValue(value);
					break;
				case INGREDIENTS:
					Ingredients ingredients = buildIngredients(reader);
					candy.setIngredients(ingredients);
					break;
				case CHOCO_TYPE:
					String chocoType = getXMLText(reader);
					for (ChocoType th : ChocoType.values()) {
						if (th.getType().equals(chocoType)) {
							((ChocolateCandy) candy).setChocoType(th);
						}
					}
					break;
				case IS_STUFFED:
					Boolean isStuffed = Boolean.parseBoolean(getXMLText(reader));
					((IcicleCandy) candy).setStuffed(isStuffed);
					break;
				case CANDIES:
					break;
				case CARBOHYDRATES:
					break;
				case CHOCOLATE_CANDY:
					break;
				case FATS:
					break;
				case FRUCTOSE:
					break;
				case ICICLE_CANDY:
					break;
				case PROTEINS:
					break;
				case SUGAR:
					break;
				case VANILIN:
					break;
				case WATER:
					break;
				default:
					break;
				}
				break;
			case XMLStreamConstants.END_ELEMENT:
				name = reader.getLocalName();
				if (name.equals(CandiesXmlTag.ICICLE_CANDY.getTag())
						|| name.equals(CandiesXmlTag.CHOCOLATE_CANDY.getTag())) {
					return candy;
				}
				break;
			}
		}
		throw new CandyException("Unknown element in tag " + tagName);
	}

	private Ingredients buildIngredients(XMLStreamReader reader) throws CandyException, XMLStreamException {
		Ingredients ingredients = new Ingredients();
		int type;
		String name;
		while (reader.hasNext()) {
			type = reader.next();
			switch (type) {
			case XMLStreamConstants.START_ELEMENT:
				name = reader.getLocalName();
				switch (CandiesXmlTag.valueOf(name.replace(HYPHEN, UNDERSCORE).toUpperCase())) {
				case FRUCTOSE:
					Integer fructose = Integer.parseInt(getXMLText(reader));
					ingredients.setFructose(fructose);
					break;
				case SUGAR:
					Integer sugar = Integer.parseInt(getXMLText(reader));
					ingredients.setSugar(sugar);
					break;
				case VANILIN:
					Integer vanilin = Integer.parseInt(getXMLText(reader));
					ingredients.setVanillin(vanilin);
					break;
				case WATER:
					Integer water = Integer.parseInt(getXMLText(reader));
					ingredients.setWater(water);
					break;
				default:
					break;
				}
				break;
			case XMLStreamConstants.END_ELEMENT:
				name = reader.getLocalName();
				if (name.equals(CandiesXmlTag.INGREDIENTS.getTag())) {
					return ingredients;
				}
				break;
			}
		}
		throw new CandyException("Unknown element in tag <ingredients>");
	}

	private Value buildValue(XMLStreamReader reader) throws XMLStreamException, CandyException {
		Value value = new Value();
		int type;
		String name;
		while (reader.hasNext()) {
			type = reader.next();
			switch (type) {
			case XMLStreamConstants.START_ELEMENT:
				name = reader.getLocalName();
				switch (CandiesXmlTag.valueOf(name.replace(HYPHEN, UNDERSCORE).toUpperCase())) {
				case CARBOHYDRATES:
					Integer carbohydrates = Integer.parseInt(getXMLText(reader));
					value.setCarbohydrates(carbohydrates);
					break;
				case FATS:
					Integer fats = Integer.parseInt(getXMLText(reader));
					value.setFats(fats);					
					break;
				case PROTEINS:
					Integer proteins = Integer.parseInt(getXMLText(reader));
					value.setProteins(proteins);
					break;
				default:
					break;
				}
				break;
			case XMLStreamConstants.END_ELEMENT:
				name = reader.getLocalName();
				if (name.equals(CandiesXmlTag.VALUE.getTag())) {
					return value;
				}
				break;
			}
		}
		throw new CandyException("Unknown element in tag <value>");
	}
	private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
		String text = null;
		if (reader.hasNext()) {
			reader.next();
			text = reader.getText();
		}
		return text;
	}
}