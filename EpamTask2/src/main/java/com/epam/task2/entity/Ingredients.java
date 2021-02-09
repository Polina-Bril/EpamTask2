package com.epam.task2.entity;

public class Ingredients {
	private int water;
	private int sugar;
	private int fructose;
	private int vanillin;

	public Ingredients() {
		super();
	}

	public Ingredients(int water, int sugar, int fructose, int vanillin) {
		super();
		this.water = water;
		this.sugar = sugar;
		this.fructose = fructose;
		this.vanillin = vanillin;
	}

	public Ingredients(int water, int sugar, int fructose) {
		super();
		this.water = water;
		this.sugar = sugar;
		this.fructose = fructose;
	}

	public Ingredients(int water, int sugar) {
		super();
		this.water = water;
		this.sugar = sugar;
	}

	public int getWater() {
		return water;
	}

	public void setWater(int water) {
		this.water = water;
	}

	public int getSugar() {
		return sugar;
	}

	public void setSugar(int sugar) {
		this.sugar = sugar;
	}

	public int getFructose() {
		return fructose;
	}

	public void setFructose(int fructose) {
		this.fructose = fructose;
	}

	public int getVanillin() {
		return vanillin;
	}

	public void setVanillin(int vanillin) {
		this.vanillin = vanillin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + fructose;
		result = prime * result + sugar;
		result = prime * result + vanillin;
		result = prime * result + water;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ingredients other = (Ingredients) obj;
		if (fructose != other.fructose)
			return false;
		if (sugar != other.sugar)
			return false;
		if (vanillin != other.vanillin)
			return false;
		if (water != other.water)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Ingredients [water=");
		builder.append(water);
		builder.append(", sugar=");
		builder.append(sugar);
		builder.append(", fructose=");
		builder.append(fructose);
		builder.append(", vanillin=");
		builder.append(vanillin);
		builder.append("]");
		return builder.toString();
	}
}
