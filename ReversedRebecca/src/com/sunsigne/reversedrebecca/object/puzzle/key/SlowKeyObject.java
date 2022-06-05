package com.sunsigne.reversedrebecca.object.puzzle.key;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;

public class SlowKeyObject extends KeyObject {

	public SlowKeyObject(Puzzle puzzle, int x, int y) {
		super(puzzle, x, y);
		divideSpeedBy(3);
	}

	////////// TICK ////////////

	int velxmax = -10;

	@Override
	public void tick() {
		super.tick();

		// limit max speed
		if (getVelX() < velxmax)
			setVelX(velxmax);
	}

}
