package com.epam.task2.parser;

import java.io.IOException;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.epam.task2.entity.Candy;
import com.epam.task2.exception.CandyException;
import com.epam.task2.handler.CandiesHandler;
import com.epam.task2.handler.CandyErrorHandler;

public class CandiesSaxBuilder extends AbstractCandyBuilder {
	public static Logger logger = LogManager.getLogger();
	private CandiesHandler handler = new CandiesHandler();
	private XMLReader reader;

	public CandiesSaxBuilder() {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser saxParser = factory.newSAXParser();
			reader = saxParser.getXMLReader();
		} catch (ParserConfigurationException | SAXException e) {
			logger.log(Level.ERROR, " parser configuration error");
		}
		reader.setErrorHandler(new CandyErrorHandler());
		reader.setContentHandler(handler);
	}

	public CandiesSaxBuilder(Set<Candy> candies) {
		super(candies);
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser saxParser = factory.newSAXParser();
			reader = saxParser.getXMLReader();
		} catch (ParserConfigurationException | SAXException e) {
			logger.log(Level.ERROR, " parser configuration error");
		}
		reader.setErrorHandler(new CandyErrorHandler());
		reader.setContentHandler(handler);
	}

	@Override
	public void buildSetCandies(String filename) throws CandyException {
		try {
			reader.parse(filename);
		} catch (IOException | SAXException e) {
			throw new CandyException("parsing error", e);
		}
		candies = handler.getCandies();
		logger.log(Level.INFO, "parsing result: " + getCandies());
	}

}