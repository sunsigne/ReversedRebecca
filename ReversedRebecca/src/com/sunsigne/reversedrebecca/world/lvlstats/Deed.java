package com.sunsigne.reversedrebecca.world.lvlstats;

import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;

public class Deed {

	public Deed() {
		goodDeed = new Translatable().getTranslatedText("LevelNoneDeed", FilePath.MENU);
		badDeed = new Translatable().getTranslatedText("LevelNoneDeed", FilePath.MENU);
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
