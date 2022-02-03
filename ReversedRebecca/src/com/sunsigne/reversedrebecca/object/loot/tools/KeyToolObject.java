package com.sunsigne.reversedrebecca.object.loot.tools;

import com.sunsigne.reversedrebecca.characteristics.KeyToolPlayer;

public class KeyToolObject extends ToolObject {

	public KeyToolObject(LVL difficulty, int x, int y) {
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
	
	@Override
	public void actionWhenLooted() {
		new KeyToolPlayer().setDifficulty(getDifficulty());
	}

}
