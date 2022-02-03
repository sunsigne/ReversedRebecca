package com.sunsigne.reversedrebecca.object.loot.tools;

import com.sunsigne.reversedrebecca.characteristics.BombToolPlayer;

public class BombToolObject extends ToolObject {

	public BombToolObject(LVL difficulty, int x, int y) {
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

	@Override
	public void actionWhenLooted() {
		new BombToolPlayer().setDifficulty(getDifficulty());
	}

}

