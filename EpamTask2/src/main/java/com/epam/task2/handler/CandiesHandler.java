package com.epam.task2.handler;

import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import com.epam.task2.entity.Candy;
import com.epam.task2.entity.ChocoType;
import com.epam.task2.entity.ChocolateCandy;
import com.epam.task2.entity.IcicleCandy;
import com.epam.task2.entity.Ingredients;
import com.epam.task2.entity.Value;

public class CandiesHandler extends DefaultHandler {
	public static Logger logger = LogManager.getLogger();
	private Set<Candy> candies;
	private Candy currentCandy;
//		private Deposit currentDeposit;
	private CandiesXmlTag currentXmlTag;
	private EnumSet<CandiesXmlTag> withText;
//		private static final String ELEMENT_BANK = "bank";
//		private static final String ELEMENT_REVOCABLE_DEPOSIT = "revocable-deposit";
//		private static final String ELEMENT_IRREVOCABLE_DEPOSIT = "irrevocable-deposit";
//		public final static String DEFAULT_ATTRIBUTE_COUNTRY = "Belarus";
	public final static String HYPHEN = "-";
	public final static String UNDERSCORE = "_";

	public CandiesHandler() {
		candies = new HashSet<Candy>();
		withText = EnumSet.range(CandiesXmlTag.NAME_ID, CandiesXmlTag.VANILIN);
	}

	public Set<Candy> getCandies() {
		return candies;
	}

	public void startElement(String uri, String localName, String qName, Attributes attrs) {
		switch (qName) {
		case "icicle-candy":
			currentCandy = new IcicleCandy();
			break;
		case "chocolate-candy":
			currentCandy = new ChocolateCandy();
			break;
		case "value":
			currentCandy.setValue(new Value());
		case "ingredients":
			currentCandy.setIngredients(new Ingredients());
		default:
			CandiesXmlTag temp = CandiesXmlTag.valueOf(qName.replace(HYPHEN, UNDERSCORE).toUpperCase());
			if (withText.contains(temp)) {
				currentXmlTag = temp;
			}
		}
	}

	public void endElement(String uri, String localName, String qName) {
		if ("icicle-candy".equals(qName) || "chocolate-candy".equals(qName)) {
			candies.add(currentCandy);
		}
	}

	public void characters(char[] ch, int start, int length) {
		String data = new String(ch, start, length).strip();
		if (currentXmlTag != null) {
			switch (currentXmlTag) {
			case NAME_ID:
				currentCandy.setName(data);
				break;
			case ENERGY:
				currentCandy.setEnergy(Integer.parseInt(data));
				break;
			case EXPIRED_DATE:
				LocalDateTime expiringData = LocalDateTime.parse(data);
				currentCandy.setExpiredDate(expiringData);
				break;
			case PRODUCTION:
				currentCandy.setProduction(data);
				break;
			case IS_STUFFED:
				((IcicleCandy) currentCandy).setStuffed(new Boolean(data));
				break;
			case PROTEINS:
				currentCandy.getValue().setProteins(Integer.parseInt(data));
				break;
			case FATS:
				currentCandy.getValue().setFats(Integer.parseInt(data));
				break;
			case CARBOHYDRATES:
				currentCandy.getValue().setCarbohydrates(Integer.parseInt(data));
				break;
			case WATER:
				currentCandy.getIngredients().setWater(Integer.parseInt(data));
				break;
			case SUGAR:
				currentCandy.getIngredients().setSugar(Integer.parseInt(data));
				break;
			case FRUCTOSE:
				currentCandy.getIngredients().setFructose(Integer.parseInt(data));
				break;
			case VANILIN:
				currentCandy.getIngredients().setVanillin(Integer.parseInt(data));
				break;
			case CHOCO_TYPE:
				for (ChocoType th : ChocoType.values()) {
			           if (th.getType().equals(data)) {
							((ChocolateCandy) currentCandy).setChocoType(th);
							break;
			           }
			       }
				break;
			default:
				logger.log(Level.ERROR, "enum constant not present");
			}
		}
		currentXmlTag = null;
	}
}