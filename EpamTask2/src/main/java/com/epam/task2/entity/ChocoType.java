package com.epam.task2.entity;

public enum ChocoType {
	DARK("DARK"), 
	WHITE("WHITE"), 
	MILK("MILK");

	private String type;
	
	ChocoType(String type) {
	this.type = type;
	}
	public String getType() {
		return type;
	}
}
