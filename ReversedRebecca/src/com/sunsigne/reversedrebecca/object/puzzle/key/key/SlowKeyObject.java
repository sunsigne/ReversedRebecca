package com.sunsigne.reversedrebecca.object.puzzle.key.key;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;

public class SlowKeyObject extends KeyObject {

	public SlowKeyObject(Puzzle puzzle) {
		super(puzzle);
		divideSpeedBy(3);
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
