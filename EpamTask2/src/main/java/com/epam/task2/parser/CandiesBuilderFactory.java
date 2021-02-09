package com.epam.task2.parser;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.task2.exception.CandyException;

public class CandiesBuilderFactory {
	public static Logger logger = LogManager.getLogger();

	CandiesBuilderFactory() {
	}

	public static AbstractCandyBuilder createCandiesBuilder(String typeParser) throws CandyException {
		AbstractCandyBuilder builder = null;
		switch (typeParser.toUpperCase()) {
		case "DOM":
			builder = new CandiesDomBuilder();
			break;
		case "STAX":
			builder = new CandiesStaxBuilder();
			break;
		case "SAX":
			builder = new CandiesSaxBuilder();
			break;
		default:
			throw new CandyException("parser " + typeParser + "is not found");
		}
		logger.log(Level.INFO, builder.getClass().getName() + " will be used");
		return builder;
	}
}
