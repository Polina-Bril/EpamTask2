package com.epam.task2.parser;

import java.util.HashSet;
import java.util.Set;

import com.epam.task2.entity.Candy;
import com.epam.task2.exception.CandyException;

public abstract class AbstractCandyBuilder {
	protected Set<Candy> candies;

	public AbstractCandyBuilder() {
		candies = new HashSet<Candy>();
	}

	public AbstractCandyBuilder(Set<Candy> candies) {
		this.candies = candies;
	}

	public Set<Candy> getCandies() {
		return candies;
	}

	public abstract void buildSetCandies(String filename) throws CandyException;
}
