package com.epam.task2.entity;

import java.time.LocalDateTime;

public abstract class Candy {
	private String name;
	private int energy;
	private String production;
	private Value value;
	private Ingredients ingredients;
	private LocalDateTime expiredDate;
	
	public Candy() {
	}
	public Candy(String name, int energy, String production, Value value, Ingredients ingredients, LocalDateTime expiredDate) {
		this.name = name;
		this.energy = energy;
		this.production = production;
		this.value = value;
		this.ingredients = ingredients;
		this.expiredDate = expiredDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getEnergy() {
		return energy;
	}
	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public String getProduction() {
		return production;
	}
	public void setProduction(String production) {
		this.production = production;
	}
	public Value getValue() {
		return value;
	}
	public void setValue(Value value) {
		this.value = value;
	}
	public Ingredients getIngredients() {
		return ingredients;
	}
	public void setIngredients(Ingredients ingredients) {
		this.ingredients = ingredients;
	}
	
	public LocalDateTime getExpiredDate() {
		return expiredDate;
	}
	public void setExpiredDate(LocalDateTime expiredDate) {
		this.expiredDate = expiredDate;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + energy;
		result = prime * result + ((expiredDate == null) ? 0 : expiredDate.hashCode());
		result = prime * result + ((ingredients == null) ? 0 : ingredients.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((production == null) ? 0 : production.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		Candy other = (Candy) obj;
		if (energy != other.energy)
			return false;
		if (expiredDate == null) {
			if (other.expiredDate != null)
				return false;
		} else if (!expiredDate.equals(other.expiredDate))
			return false;
		if (ingredients == null) {
			if (other.ingredients != null)
				return false;
		} else if (!ingredients.equals(other.ingredients))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (production == null) {
			if (other.production != null)
				return false;
		} else if (!production.equals(other.production))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Candy [name=");
		builder.append(name);
		builder.append(", energy=");
		builder.append(energy);
		builder.append(", production=");
		builder.append(production);
		builder.append(", value=");
		builder.append(value);
		builder.append(", ingredients=");
		builder.append(ingredients);
		builder.append(", expiredDate=");
		builder.append(expiredDate);
		builder.append("]");
		return builder.toString();
	}
}
