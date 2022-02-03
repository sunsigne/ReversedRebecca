package com.sunsigne.reversedrebecca.object.loot.tools;

import com.sunsigne.reversedrebecca.ressources.DIFFICULTY;

public class KeyToolObject extends ToolObject {

	public KeyToolObject(DIFFICULTY difficulty, int x, int y) {
		super(difficulty, x, y);
	}

	@Override
	public String getPuzzlerName() {
		return "door";
	}

	@Override
	public String getName() {
		return "key";
	}

}
