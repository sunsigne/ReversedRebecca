package com.sunsigne.reversedrebecca.puzzle.dig;

import java.awt.Color;
import java.awt.Graphics;

import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.render.TransluantLayer;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.PuzzleFactory;

public abstract class DigPuzzle extends Puzzle {

	public DigPuzzle(GenericListener actionOnWinning) {
		super(actionOnWinning);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "dig";
	}

	////////// FACTORY ////////////

	@Override
	public PuzzleFactory getFactory() {
		return new DigPuzzleFactory();
	}

	////////// PUZZLE ////////////

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		Color brown = new Color(50, 30, 15, 240);
		new TransluantLayer().drawPuzzle(g, brown);
	}

}
