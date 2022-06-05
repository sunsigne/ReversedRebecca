package com.sunsigne.reversedrebecca.object.puzzle.key;

import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;

public class SlowKeyObject extends KeyObject {

	public SlowKeyObject(Puzzle puzzle, int x, int y) {
		super(puzzle, x, y);
		speed = speed / 3;
		setVelY(new RandomGenerator().getBoolean() ? speed : -speed);
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
