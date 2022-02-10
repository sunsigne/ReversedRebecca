package com.sunsigne.reversedrebecca.puzzle;

import java.awt.Color;
import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.puzzle.KillPuzzleObject;
import com.sunsigne.reversedrebecca.object.puzzle.key.KeyPuzzleObject;
import com.sunsigne.reversedrebecca.object.puzzle.key.LockPuzzleObject;
import com.sunsigne.reversedrebecca.pattern.GenericListener;
import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.pattern.render.TransluantLayer;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor;

public class KeyPuzzle extends Puzzle {

	public KeyPuzzle(LVL difficulty, GenericListener actionOnWinning) {
		super(difficulty, actionOnWinning);
		new GameCursor().setVisible(false);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "key";
	}

	////////// PUZZLE ////////////

	@Override
	public void createPuzzle() {
		LAYER.PUZZLE.addObject(new LockPuzzleObject(this, getCol(1), getRow(4)));
		LAYER.PUZZLE
				.addObject(new KeyPuzzleObject(this, getCol(12), getRow(new RandomGenerator().getIntBetween(1, 6))));

		for (int row = 1; row <= 6; row++) {
			LAYER.PUZZLE.addObject(new KillPuzzleObject(this, getCol(0), getRow(row)));
		}
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		Color green = new Color(15, 45, 10, 240);
		new TransluantLayer().drawPuzzle(g, green);
	}

}
