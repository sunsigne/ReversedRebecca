package com.sunsigne.reversedrebecca.object.puzzle.bomb;

import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.system.Size;

public class CrazyBombObject extends ShrinkBombObject {

	public CrazyBombObject(Puzzle puzzle, int x, int y) {
		super(puzzle, x, y);
		defineNewXSpeed(Size.XS / 4);
	}

	////////// NAME ////////////
	
	@Override
	protected String getName(){
		return "BOMB CRAZY";
	}
	
	////////// VELOCITY ////////////

	private int speed = Size.XS / 4;
	
	protected void defineNewXSpeed(int speed) {
		this.speed = speed;
		setVelX(new RandomGenerator().getBoolean() ? speed : -speed);
	}

	////////// TICK ////////////

	private final int xmin = Size.S + getPuzzle().getCol(1);
	private final int xmax = getPuzzle().getCol(12);

	@Override
	public void tick() {
		super.tick();

		if (hasExploded())
			return;

		// goes left and right
		if (getX() >= xmax)
			setVelX(-speed);
		if (getX() <= xmin)
			setVelX(speed);
	}

}
