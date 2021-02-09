package com.epam.task2.entity;

import java.time.LocalDateTime;

public class ChocolateCandy extends Candy {
	private ChocoType chocoType;

	public ChocolateCandy() {
	}

	public ChocolateCandy(String name, int energy, String production, Value value, Ingredients ingredients,
			LocalDateTime expiredDate, ChocoType chocoType) {
		super(name, energy, production, value, ingredients, expiredDate);
		this.chocoType = chocoType;
	}

	public ChocoType getChocoType() {
		return chocoType;
	}

	public void setChocoType(ChocoType chocoType) {
		this.chocoType = chocoType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((chocoType == null) ? 0 : chocoType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChocolateCandy other = (ChocolateCandy) obj;
		if (chocoType != other.chocoType)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.append("ChocolateCandy [chocoType=");
		builder.append(chocoType);
		builder.append("]");
		return builder.toString();
	}
}
