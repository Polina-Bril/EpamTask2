package com.epam.task2.entity;

import java.time.LocalDateTime;

public class IcicleCandy extends Candy {
	private boolean isStuffed;

	public IcicleCandy() {
	}

	public IcicleCandy(String name, int energy, String production, Value value, Ingredients ingredients,
			LocalDateTime expiredDate, boolean isStuffed) {
		super(name, energy, production, value, ingredients, expiredDate);
		this.isStuffed = isStuffed;
	}

	public boolean isStuffed() {
		return isStuffed;
	}

	public void setStuffed(boolean isStuffed) {
		this.isStuffed = isStuffed;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (isStuffed ? 1231 : 1237);
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
		IcicleCandy other = (IcicleCandy) obj;
		if (isStuffed != other.isStuffed)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.append("IcicleCandy [isStuffed=");
		builder.append(isStuffed);
		builder.append("]");
		return builder.toString();
	}
}
