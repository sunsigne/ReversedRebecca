package com.sunsigne.reversedrebecca.object.puzzle.bomb;

import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.system.Size;

public class MovingBombObject extends BombObject {

	protected MovingBombObject(Puzzle puzzle, boolean critical, int x, int y, int w, int h) {
		super(puzzle, critical, x, y, w, h);
		defineNewYSpeed(Size.XS / 5);
	}
	
	public MovingBombObject(Puzzle puzzle, boolean critical, int x, int y) {
		this(puzzle, critical, x, y, 2 * Size.L, 2 * Size.L);
	}
	
	////////// NAME ////////////
	
	@Override
	protected String getName(){
		return "BOMB MOVING";
	}
	
	////////// VELOCITY ////////////

	private int speed = Size.XS / 5;
	
	protected void defineNewYSpeed(int speed) {
		this.speed = speed;
		setVelY(new RandomGenerator().getBoolean() ? speed : -speed);
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
