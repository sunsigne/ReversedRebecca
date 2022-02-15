package com.sunsigne.reversedrebecca.object.puzzle.bomb;

import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.system.Size;

public class MovingBombObject extends BombObject {

	public int speed = Size.XS / 5;

	protected MovingBombObject(Puzzle puzzle, int x, int y, int w, int h) {
		super(puzzle, x, y, w, h);
		setVelY(new RandomGenerator().getBoolean() ? speed : -speed);
	}
	
	public MovingBombObject(Puzzle puzzle, int x, int y) {
		this(puzzle, x, y, 2 * Size.L, 2 * Size.L);
	}

	////////// TICK ////////////

	protected int ymin = getPuzzle().getRow(1);
	protected int ymax = getPuzzle().getRow(5);

	@Override
	public void tick() {
		super.tick();

		if (hasExploded()) {
			setMotionless();
			return;
		}

		// goes up and down
		if (getY() > ymax)
			setVelY(-speed);
		if (getY() < ymin)
			setVelY(speed);
	}

}
