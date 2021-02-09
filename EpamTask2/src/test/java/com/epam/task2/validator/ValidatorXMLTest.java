package com.epam.task2.validator;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.epam.task2.exception.CandyException;

public class ValidatorXMLTest {
	ValidatorXml validator;

	@BeforeClass
	public void setUp() {
		validator = new ValidatorXml();
	}
	@Test
	public void validateXmlTest() throws CandyException {
		String filePath = "resources/data/candies.xml";
		String schemaPath = "resources/data/candies.xsd";
		Assert.assertTrue(validator.validateXml(filePath, schemaPath));
	}
	@Test
	public void validateXmlOneMoreTest() throws CandyException {
		String filePath = null;
		String schemaPath = null;
		Assert.assertFalse(validator.validateXml(filePath, schemaPath));
	}
	@AfterClass
	public void tierDown() {
		validator = null;
	}
}
