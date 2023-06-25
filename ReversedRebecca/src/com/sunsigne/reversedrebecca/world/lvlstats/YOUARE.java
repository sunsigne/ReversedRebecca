package com.sunsigne.reversedrebecca.world.lvlstats;

public enum YOUARE {

	PSYCHOPATH("Psychopath"), SADISTIC("Sadistic"), MEAN("Mean"), NEUTRAL("Neutral"), NICE("Nice"),
	ANGELIC("Angelic");

	private String name;

	YOUARE(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
