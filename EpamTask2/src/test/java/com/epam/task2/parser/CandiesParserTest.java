package com.epam.task2.parser;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.task2.entity.Candy;
import com.epam.task2.entity.ChocoType;
import com.epam.task2.entity.ChocolateCandy;
import com.epam.task2.entity.IcicleCandy;
import com.epam.task2.entity.Ingredients;
import com.epam.task2.entity.Value;
import com.epam.task2.exception.CandyException;

public class CandiesParserTest {
	final Logger logger = LogManager.getLogger();
	private Set<Candy> candySet;
	static final String TEST_FILENAME = "resources/data/candies.xml";

	@BeforeMethod
	public void setUp() {
		candySet = new HashSet<>();
		candySet.add(new IcicleCandy("Nyam-nyam", 600, "Spartak", new Value(40, 100, 15),
				new Ingredients(115, 20, 10, 5), LocalDateTime.of(2020, 9, 19, 14, 5), true));
		candySet.add(new ChocolateCandy("Grilyazh", 700, "Komunarka", new Value(45, 10, 115),
				new Ingredients(15, 120, 5), LocalDateTime.of(2021, 1, 7, 15, 15), ChocoType.DARK));
		candySet.add(new IcicleCandy("Vzletnaya", 600, "Spartak", new Value(140, 10, 150),
				new Ingredients(115, 20), LocalDateTime.of(2020, 12, 1, 17, 25), true));
		}

	@AfterMethod
	public void tearDown() {
		candySet = null;
	}

	@Test
	public void buildSetAccessoryDomTest() throws CandyException {
		AbstractCandyBuilder builder = CandiesBuilderFactory.createCandiesBuilder("DOM");
		builder.buildSetCandies(TEST_FILENAME);
		logger.info("{}", candySet);
		logger.info("{}", builder.getCandies());
		Set<Candy> expected = candySet;
		Set<Candy> actual = builder.getCandies();
		Assert.assertEquals(actual, expected);
	}

	@Test
	public void buildSetAccessoryStaxTest() throws CandyException {
		AbstractCandyBuilder builder = CandiesBuilderFactory.createCandiesBuilder("STAX");
		builder.buildSetCandies(TEST_FILENAME);
		Set<Candy> expected = candySet;
		Set<Candy> actual = builder.getCandies();
		Assert.assertEquals(actual, expected);
	}

	@Test
	public void builtSetAccessorySaxTest() throws CandyException {
		AbstractCandyBuilder builder = CandiesBuilderFactory.createCandiesBuilder("SAX");
		builder.buildSetCandies(TEST_FILENAME);
		Set<Candy> expected = candySet;
		Set<Candy> actual = builder.getCandies();
		Assert.assertEquals(actual, expected);
	}
}
