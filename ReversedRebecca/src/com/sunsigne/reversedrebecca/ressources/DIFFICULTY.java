package com.sunsigne.reversedrebecca.ressources;

public enum DIFFICULTY {
	
	NULL("null"),
	CYAN("cyan"),
	GREEN("green"),
	YELLOW("yellow"),
	ORANGE("orange"),
	RED("red"),
	// special difficulty, for boss or senario only
	PURPLE("purple");
	
	
	private String name;
	
	DIFFICULTY(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
