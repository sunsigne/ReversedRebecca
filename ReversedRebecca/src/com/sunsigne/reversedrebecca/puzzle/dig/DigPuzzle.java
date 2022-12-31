package com.sunsigne.reversedrebecca.puzzle.dig;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.puzzle.WallPuzzle;
import com.sunsigne.reversedrebecca.object.puzzle.dig.BuriedExitObject;
import com.sunsigne.reversedrebecca.object.puzzle.dig.DirtObject;
import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.render.TransluantLayer;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.PuzzleFactory;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;

public abstract class DigPuzzle extends Puzzle {

	public DigPuzzle(int criticalChance, GenericListener actionOnWinning) {
		super(criticalChance, actionOnWinning);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "dig";
	}

	protected abstract int getSize();

	////////// FACTORY ////////////

	@Override
	public PuzzleFactory getFactory() {
		return new DigPuzzleFactory();
	}

	////////// PUZZLE ////////////

	@Override
	protected void createWallBorder() {
		super.createWallBorder();

		Handler handler = LAYER.PUZZLE.getHandler();
		BufferedImage image = getWallTexture();

		for (int row = 0; row < 8; row++) {
			handler.addObject(new WallPuzzle(image, getCol(5), getRow(row)));
			handler.addObject(new WallPuzzle(image, getCol(6), getRow(row)));
		}
	}

	private GameList<DirtObject> list = new GameList<>(LISTTYPE.ARRAY);

	protected void createDirt(int col, int row) {
		DirtObject dirt = new DirtObject(this, getSize());
		list.addObject(dirt);
		dirt.setX(getCol(col));
		dirt.setY(getRow(row));
		LAYER.PUZZLE.addObject(dirt);
	}

	protected void createExit() {
		BuriedExitObject exit = new BuriedExitObject(this, getSize());
		DirtObject dirt = new RandomGenerator().getElementFromList(list);
		dirt.setBuriedObject(exit, getSize());
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		Color brown = new Color(50, 30, 15, 240);
		new TransluantLayer().drawPuzzle(g, brown);
	}

}
