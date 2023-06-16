package com.sunsigne.reversedrebecca.world.lvlstats;

import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;

public class Deed {

	public Deed() {
		goodText = new Translatable().getTranslatedText("LevelNoneDeed", FilePath.MENU);
		badText = new Translatable().getTranslatedText("LevelNoneDeed", FilePath.MENU);
	}

	private int karmaWeight;

	public int getKarmaWeight() {
		return karmaWeight;
	}

	////////// GOOD ////////////

	private String goodText;
	private int goodWeight;

	public String getGoodText() {
		return goodText;
	}

	public void setGoodDeed(int weight, String text) {
		karmaWeight = karmaWeight + weight;

		if (weight < goodWeight)
			return;

		this.goodWeight = weight;
		this.goodText = text;
	}

	////////// BAD ////////////

	private String badText;
	private int badWeight;

	public String getBadText() {
		return badText;
	}

	public void setBadDeed(int weight, String text) {
		if (weight >= 999)
			weight = weight * 10;
		
		karmaWeight = karmaWeight - weight;

		if (weight < badWeight)
			return;

		this.badWeight = weight;
		this.badText = text;
	}

}
