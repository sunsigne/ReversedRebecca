package com.sunsigne.reversedrebecca.world.lvlstats;

import com.sunsigne.reversedrebecca.ressources.lang.Translatable;

public class Deed {

	private String file = "menu.csv";

	public Deed() {
		goodDeed = new Translatable().getTranslatedText("LevelNoneDeed", file);
		badDeed = new Translatable().getTranslatedText("LevelNoneDeed", file);
	}

	////////// GOOD ////////////

	private String goodDeed;
	private int goodWeight;

	public String getGoodDeed() {
		return goodDeed;
	}

	public void setGoodDeed(int weight, String goodDeed) {
		if (weight < goodWeight)
			return;

		this.goodWeight = weight;
		this.goodDeed = goodDeed;
	}

	////////// BAD ////////////

	private String badDeed;
	private int badWeight;

	public String getBadDeed() {
		return badDeed;
	}

	public void setBadDeed(int weight, String badDeed) {
		if (weight < badWeight)
			return;

		this.badWeight = weight;
		this.badDeed = badDeed;
	}

}
