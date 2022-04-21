package com.sunsigne.reversedrebecca.puzzle.bomb;

import java.awt.Color;
import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.puzzle.bomb.BombObject;
import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.Window;

public abstract class BombPuzzle extends Puzzle {

	public BombPuzzle(GenericListener actionOnWinning) {
		super(actionOnWinning);
		
		createBombs();
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "bomb";
	}

	////////// PUZZLE ////////////

	private BombObject[] bomb = new BombObject[4];

	public abstract BombObject getBomb(Puzzle puzzle, int x, int y);
	
	protected void createBombs() {
		for (int index = 0; index < 4; index++) {

			int col = Size.S + getCol(1 + index * 3);
			int radRow = Size.S + getRow(new RandomGenerator().getIntBetween(1, 4));

			bomb[index] = getBomb(this, col, radRow);
			LAYER.PUZZLE.addObject(bomb[index]);
		}
	}

	protected void setRandomMaxCountBetween(int a, int b) {
		for (int index = 0; index < 4; index++) {
			int count = new RandomGenerator().getIntBetween(a, b);
			bomb[index].setMaxCount(count);
			bomb[index].setCount(count);
		}
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		for (int index = 0; index < 4; index++) {
			if (bomb[index] == null)
				return;

			if (!bomb[index].hasExploded())
				return;
		}
		closePuzzle(true);
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		Color red = new Color(50, 10, 10, 240);
		g.setColor(red);
		g.fillRect(Size.L, Size.L, Window.WIDHT - 2 * Size.L, Window.HEIGHT - 2 * Size.L);
	}

}
