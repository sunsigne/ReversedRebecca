package com.sunsigne.reversedrebecca.puzzle.dig;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.puzzle.WallPuzzle;
import com.sunsigne.reversedrebecca.object.puzzle.dig.BuriedObject;
import com.sunsigne.reversedrebecca.object.puzzle.dig.DirtObject;
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
	
	protected void createDirt(int col, int row, BuriedObject buriedObject) {
		DirtObject dirt = new DirtObject(this, buriedObject, getSize());
		dirt.setX(getCol(col));
		dirt.setY(getRow(row));
		LAYER.PUZZLE.addObject(dirt);
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		Color brown = new Color(50, 30, 15, 240);
		new TransluantLayer().drawPuzzle(g, brown);
	}

}
