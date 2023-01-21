package com.sunsigne.reversedrebecca.object.puzzler.hole.upward;

import com.sunsigne.reversedrebecca.object.puzzler.hole.downward.HoleObject;

public class HoleUpwardObject extends HoleObject {

	public HoleUpwardObject(DEV_LVL devDifficulty, DIRECTION facing, int x, int y) {
		super(devDifficulty, facing, x, y);
	}

	public HoleUpwardObject(LVL difficulty, DIRECTION facing, int x, int y) {
		super(difficulty, facing, x, y);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "hole_upward" + "_" + getFacing().getName();
	}

}
