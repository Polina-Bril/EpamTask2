package com.epam.task2.entity;

public class Value {
	private int proteins;
	private int fats;
	private int carbohydrates;
	
	public Value() {
	}

	public Value(int proteins, int fats, int carbohydrates) {
		super();
		this.proteins = proteins;
		this.fats = fats;
		this.carbohydrates = carbohydrates;
	}

	public int getProteins() {
		return proteins;
	}

	public void setProteins(int proteins) {
		this.proteins = proteins;
	}

	public int getFats() {
		return fats;
	}

	public void setFats(int fats) {
		this.fats = fats;
	}

	public int getCarbohydrates() {
		return carbohydrates;
	}

	public void setCarbohydrates(int carbohydrates) {
		this.carbohydrates = carbohydrates;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + carbohydrates;
		result = prime * result + fats;
		result = prime * result + proteins;
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
		Value other = (Value) obj;
		if (carbohydrates != other.carbohydrates)
			return false;
		if (fats != other.fats)
			return false;
		if (proteins != other.proteins)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder("Value [proteins=");
		s.append(proteins)
			.append(", fats=")
			.append(fats)
			.append(", carbohydrates=")
			.append(carbohydrates)
			.append("]");
		return s.toString();
		}
}
