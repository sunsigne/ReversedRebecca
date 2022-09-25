package com.sunsigne.reversedrebecca.world.lvlstats;

public enum YOUARE {

	FAST("Fast"), PRAGMATIC("Pragmatic"), METICULOUS("Meticulous"),
	PSYCHOPATH("Psychopath"), MEAN("Mean"), NICE("Nice");

	private String name;

	YOUARE(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
