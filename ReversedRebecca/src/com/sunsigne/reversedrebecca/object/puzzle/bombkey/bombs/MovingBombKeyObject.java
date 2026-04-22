package com.sunsigne.reversedrebecca.object.puzzle.bombkey.bombs;

import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.system.Size;

public class MovingBombKeyObject extends BombKeyObject {

	protected MovingBombKeyObject(Puzzle puzzle, boolean critical, int x, int y, int w, int h) {
		super(puzzle, critical, x, y, w, h);
		defineNewYSpeed(Size.XS / 5);
	}

	public MovingBombKeyObject(Puzzle puzzle, boolean critical, int x, int y) {
		this(puzzle, critical, x, y, 3 * Size.XL, 3 * Size.XL);
	}

	////////// NAME ////////////

	protected String getName() {
		return "BOMBKEY MOVING";
	}

	////////// VELOCITY ////////////

	private int speed = Size.XS / 5;

	protected void defineNewYSpeed(int speed) {
		this.speed = speed;
		setVelY(new RandomGenerator().getBoolean() ? speed : -speed);
	}

	////////// TICK ////////////

	protected int ymin = getPuzzle().getRow(1);
	protected int ymax = getPuzzle().getRow(3);

	@Override
	public void tick() {
		super.tick();
		goesUpAndDown();
	}
	
	protected void goesUpAndDown() {
		if (getY() > ymax)
			setVelY(-speed);
		if (getY() < ymin)
			setVelY(speed);
	}

}
