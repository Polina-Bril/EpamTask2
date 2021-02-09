package com.epam.task2.handler;

public enum CandiesXmlTag {
	CANDIES("candies"), 
	CHOCOLATE_CANDY("chocolate-candy"), 
	ICICLE_CANDY("icicle-candy"), 
	NAME_ID("name-id"), 
	ENERGY("energy"), 
	PRODUCTION("production"), 
	VALUE("value"), 
	INGREDIENTS("ingredients"), 
	EXPIRED_DATE("expired-date"), 
	IS_STUFFED("is-stuffed"), 
	CHOCO_TYPE("chocoType"), 
	PROTEINS("proteins"), 
	FATS("fats"), 
	CARBOHYDRATES("carbohydrates"), 
	WATER("water"), 
	SUGAR("sugar"), 
	FRUCTOSE("fructose"), 
	VANILIN("vanilin");
	
	private final String tag;
	
	CandiesXmlTag(String tag) {
		this.tag=tag;
	}
	public String getTag() {
		return tag;
	}
}
