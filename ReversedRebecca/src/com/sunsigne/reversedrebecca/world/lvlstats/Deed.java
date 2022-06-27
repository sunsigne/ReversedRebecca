package com.sunsigne.reversedrebecca.world.lvlstats;

import com.sunsigne.reversedrebecca.ressources.lang.Translatable;

public class Deed {

	private String file = "menu.csv";

	public Deed() {
		goodDeed = new Translatable().getTranslatedText("LevelNoneDeed", file);
		badDeed = new Translatable().getTranslatedText("LevelNoneDeed", file);
	}

	////////// GOOD ////////////

	private String goodDeed, badDeed;
	private int goodWeight, badWeight;

	public String getGoodDeed() {
		return goodDeed;
	}

	public String getBadDeed() {
		return badDeed;
	}

	public int getGoodWeight() {
		return goodWeight;
	}

	public int getBadWeight() {
		return badWeight;
	}

	public void setGoodDeed(int weight, String goodDeed) {
		if (weight < goodWeight)
			return;

		this.goodWeight = weight;
		this.goodDeed = goodDeed;
	}

	public void setBadDeed(int weight, String badDeed) {
		if (weight < badWeight)
			return;

		this.badWeight = weight;
		this.badDeed = badDeed;
	}

}
