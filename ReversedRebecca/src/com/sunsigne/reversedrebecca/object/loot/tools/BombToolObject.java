package com.sunsigne.reversedrebecca.object.loot.tools;

import com.sunsigne.reversedrebecca.characteristics.BombToolPlayer;
import com.sunsigne.reversedrebecca.characteristics.ToolPlayer;

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
	public ToolPlayer getToolPlayer() {
		return new BombToolPlayer();
	}

}
