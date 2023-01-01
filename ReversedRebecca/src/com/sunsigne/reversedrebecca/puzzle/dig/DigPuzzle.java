package com.sunsigne.reversedrebecca.puzzle.dig;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.puzzle.WallPuzzle;
import com.sunsigne.reversedrebecca.object.puzzle.dig.BuriedExitObject;
import com.sunsigne.reversedrebecca.object.puzzle.dig.DigHandToolObject;
import com.sunsigne.reversedrebecca.object.puzzle.dig.DigMouseObject;
import com.sunsigne.reversedrebecca.object.puzzle.dig.DigPickaxeToolObject;
import com.sunsigne.reversedrebecca.object.puzzle.dig.DigShovelToolObject;
import com.sunsigne.reversedrebecca.object.puzzle.dig.DigToolObject;
import com.sunsigne.reversedrebecca.object.puzzle.dig.DirtObject;
import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.render.TransluantLayer;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.PuzzleFactory;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;

public abstract class DigPuzzle extends Puzzle {

	public DigPuzzle(int criticalChance, GenericListener actionOnWinning) {
		super(criticalChance, actionOnWinning);
		
		new GameCursor().setCursor(null);
		LAYER.PUZZLE.addObject(new DigMouseObject(this, getSize() / 2, getSize() / 2));
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
		DirtObject dirt = new DirtObject(this, getSize(), getSize());
		list.addObject(dirt);
		dirt.setX(getCol(col));
		dirt.setY(getRow(row));
		LAYER.PUZZLE.addObject(dirt);
	}

	protected void createExit() {
		BuriedExitObject exit = new BuriedExitObject(this, getSize(), getSize());
		DirtObject dirt = new RandomGenerator().getElementFromList(list);
		dirt.setBuriedObject(exit, getSize(), getSize());
	}

	private DIG_STATE state = DIG_STATE.DIG;

	public DIG_STATE getState() {
		return state;
	}

	public void setState(DIG_STATE state) {
		this.state = state;
	}

	private DigToolObject getTool(DIG_STATE dig_state) {
		switch (dig_state) {
		case HAND:
			return new DigHandToolObject(this);
			
		case PICK:
			return new DigPickaxeToolObject(this);
			
		case DIG:
		default:
			return new DigShovelToolObject(this);
		}
	}

	protected void createTool(int col, int row, DIG_STATE dig_state) {
		DigToolObject tool = getTool(dig_state);
		tool.setX(getCol(col));
		tool.setY(getRow(row));
		LAYER.PUZZLE.addObject(tool);
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		Color brown = new Color(50, 30, 15, 240);
		new TransluantLayer().drawPuzzle(g, brown);
	}

}
