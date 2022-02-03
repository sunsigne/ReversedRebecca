package com.sunsigne.reversedrebecca.object.loot.tools;

import com.sunsigne.reversedrebecca.ressources.DIFFICULTY;

public class BombToolObject extends ToolObject {

	public BombToolObject(DIFFICULTY difficulty, int x, int y) {
		super(difficulty, x, y);
	}

	@Override
	public String getPuzzlerName() {
		return "rubble";
	}

	@Override
	public String getName() {
		return "bomb";
	}

}

