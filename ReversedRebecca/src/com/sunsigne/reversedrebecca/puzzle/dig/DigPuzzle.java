package com.sunsigne.reversedrebecca.puzzle.dig;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.puzzle.WallPuzzle;
import com.sunsigne.reversedrebecca.object.puzzle.dig.BuriedExitObject;
import com.sunsigne.reversedrebecca.object.puzzle.dig.BuriedObject;
import com.sunsigne.reversedrebecca.object.puzzle.dig.DigHandToolObject;
import com.sunsigne.reversedrebecca.object.puzzle.dig.DigMouseObject;
import com.sunsigne.reversedrebecca.object.puzzle.dig.DigPickaxeToolObject;
import com.sunsigne.reversedrebecca.object.puzzle.dig.DigShovelToolObject;
import com.sunsigne.reversedrebecca.object.puzzle.dig.DigToolObject;
import com.sunsigne.reversedrebecca.object.puzzle.dig.DirtObject;
import com.sunsigne.reversedrebecca.object.puzzle.dig.RockObject;
import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.render.TransluantLayer;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.PuzzleFactory;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;

public abstract class DigPuzzle extends Puzzle {

	public DigPuzzle(int criticalChance, GenericListener actionOnWinning) {
		super(criticalChance, actionOnWinning);

		new GameCursor().setCursor(null);
		LAYER.PUZZLE.addObject(new DigMouseObject(this, getSize() / 2, getSize() / 2));
	}

	////////// USEFULL ////////////

	public int getCol(float col) {
		return (int) (2 * Size.XS + col * Size.L);
	}

	public int getRow(float row) {
		return (int) (Size.XS + row * Size.L);
	}
	
	////////// NAME ////////////

	@Override
	public String getName() {
		return "dig";
	}

	public abstract int getSize();

	////////// FACTORY ////////////

	@Override
	public PuzzleFactory getFactory() {
		return new DigPuzzleFactory();
	}

	////////// PUZZLE ////////////

	private DIG_STATE state = DIG_STATE.DIG;

	public DIG_STATE getState() {
		return state;
	}

	public void setState(DIG_STATE state) {
		this.state = state;
	}

	public DigToolObject getTool(DIG_STATE dig_state, int x_pos_in_menu, int y_pos_in_menu, boolean selectable) {
		switch (dig_state) {
		case HAND:
			return new DigHandToolObject(this, x_pos_in_menu, y_pos_in_menu, getSize(), getSize(), selectable);

		case PICK:
			return new DigPickaxeToolObject(this, x_pos_in_menu, y_pos_in_menu, getSize(), getSize(), selectable);

		case DIG:
		default:
			return new DigShovelToolObject(this, x_pos_in_menu, y_pos_in_menu, getSize(), getSize(), selectable);
		}
	}

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
		createDirt((float) col, (float) row);
	}
	
	protected void createDirt(float col, float row) {
		DirtObject dirt = new DirtObject(this, getSize(), getSize());
		list.addObject(dirt);
		dirt.setX(getCol(col));
		dirt.setY(getRow(row));
		LAYER.PUZZLE.addObject(dirt);
	}

	protected void createRock(int amount) {
		for (int index = 0; index < amount; index++) {
			DirtObject dirt = new RandomGenerator().getElementFromList(list);

			RockObject rock = new RockObject(this, getSize(), getSize());
			rock.setX(dirt.getX());
			rock.setY(dirt.getY());

			dirt.setBuriedObject(rock, getSize(), getSize());
		}
	}

	protected void createTool(int col, int row, DIG_STATE dig_state) {
		DigToolObject tool = getTool(dig_state, getCol(col), getRow(row), true);
		tool.setX(getCol(col));
		tool.setY(getRow(row));
		LAYER.PUZZLE.addObject(tool);
	}

	protected void createBuriedTool(int col, int row, DIG_STATE dig_state) {
		DigToolObject tool = getTool(dig_state, getCol(col), getRow(row), false);

		DirtObject dirt = new RandomGenerator().getElementFromList(list);
		dirt.setBuriedObject(tool, getSize(), getSize());
	}

	protected void createExit() {
		BuriedExitObject exit = new BuriedExitObject(this, getSize(), getSize());
		DirtObject dirt;

		do {
			dirt = new RandomGenerator().getElementFromList(list);
		} while (dirt.getBuriedObject() instanceof DigToolObject);

		BuriedObject buried = dirt.getBuriedObject();

		if (buried instanceof RockObject) {
			RockObject rock = (RockObject) buried;
			rock.setBuriedObject(exit, getSize(), getSize());
		} else
			dirt.setBuriedObject(exit, getSize(), getSize());
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		Color brown = new Color(50, 30, 15, 240);
		new TransluantLayer().drawPuzzle(g, brown);
	}

}
