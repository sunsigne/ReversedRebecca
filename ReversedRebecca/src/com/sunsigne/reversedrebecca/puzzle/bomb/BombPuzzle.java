package com.sunsigne.reversedrebecca.puzzle.bomb;

import java.awt.Color;
import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.puzzle.bomb.BigBombObject;
import com.sunsigne.reversedrebecca.object.puzzle.bomb.BombObject;
import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.render.TransluantLayer;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.PuzzleFactory;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public abstract class BombPuzzle extends Puzzle {

	public BombPuzzle(GenericListener actionOnWinning) {
		super(actionOnWinning);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "bomb";
	}

	////////// FACTORY ////////////

	@Override
	public PuzzleFactory getFactory() {
		return new BombPuzzleFactory();
	}

	////////// PUZZLE ////////////

	private BombObject[] bomb = new BombObject[getBombAmount()];

	public abstract BombObject getBomb(Puzzle puzzle, int x, int y);

	public abstract int getBombAmount(); // 3, 4 or 6

	private int getColGap() {
		switch (getBombAmount()) {
		case 3:
			return Size.S;
		case 4:
			return 0;
		case 6:
			return -Size.L - Size.S / 4;
		}

		return 0;
	}

	protected void createBombs() {
		int colGap = getColGap();
		int maxRow = getBomb(this, 0, 0) instanceof BigBombObject ? 3 : 4;

		for (int index = 0; index < getBombAmount(); index++) {

			int col = Size.S + colGap * index + getCol(1 + index * 3);
			int radRow = Size.S + getRow(new RandomGenerator().getIntBetween(1, maxRow));

			bomb[index] = getBomb(this, col, radRow);
			LAYER.PUZZLE.addObject(bomb[index]);
		}
	}

	protected void setRandomMaxCountBetween(int a, int b) {
		for (int index = 0; index < getBombAmount(); index++) {
			int count = new RandomGenerator().getIntBetween(a, b);
			bomb[index].setMaxCount(count);
			bomb[index].setCount(count);
		}
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		// prevent puzzle to close before bomb creation
		if (bomb[0] == null)
			return;

		for (Updatable tempUpdatable : LAYER.PUZZLE.getHandler().getList()) {
			if (tempUpdatable instanceof BombObject == false)
				continue;

			BombObject bomb = (BombObject) tempUpdatable;
			if (bomb.hasExploded() == false)
				return;
		}

		// happens when all bombs has exploded
		closePuzzle(true);
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		Color red = new Color(50, 10, 10, 240);
		new TransluantLayer().drawPuzzle(g, red);
	}

}
