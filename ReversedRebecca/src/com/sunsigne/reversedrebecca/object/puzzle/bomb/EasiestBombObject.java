package com.sunsigne.reversedrebecca.object.puzzle.bomb;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.system.Size;

public class EasiestBombObject extends BigBombObject {

	public EasiestBombObject(Puzzle puzzle, int x, int y) {
		super(puzzle, x, y);
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		super.tick();

		setY(getPuzzle().getRow(2) + Size.S);
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
	}

}
