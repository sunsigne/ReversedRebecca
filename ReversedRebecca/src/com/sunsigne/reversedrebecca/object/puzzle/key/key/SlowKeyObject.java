package com.sunsigne.reversedrebecca.object.puzzle.key.key;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;

public class SlowKeyObject extends KeyObject {

	public SlowKeyObject(Puzzle puzzle) {
		super(puzzle);
		divideSpeedBy(3);
	}

	////////// NAME ////////////

	protected String getName() {
		return "KEY SLOW";
	}
	
	////////// TICK ////////////

	int velXmax = -10;

	@Override
	public void tick() {
		super.tick();

		// limit max speed
		if (getVelX() < velXmax)
			setVelX(velXmax);
	}

}
