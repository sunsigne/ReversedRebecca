package com.sunsigne.reversedrebecca.puzzle.cowboy;

import java.awt.Color;
import java.awt.Graphics;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.puzzle.cowboy.CowboyBadGuy;
import com.sunsigne.reversedrebecca.object.puzzle.cowboy.CowboyCursorObject;
import com.sunsigne.reversedrebecca.object.puzzle.cowboy.CowboyTarget;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.render.TransluantLayer;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.PuzzleFactory;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor;
import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor.CURSOR_TYPE;

public abstract class CowboyPuzzle extends Puzzle {

	public CowboyPuzzle(ToolPlayer toolPlayer, GenericListener actionOnWinning, GenericListener actionOnLosing) {
		super(toolPlayer, actionOnWinning, actionOnLosing);

		new GameCursor().setCursor(CURSOR_TYPE.POINTER);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "cowboy";
	}

	////////// FACTORY ////////////

	@Override
	public PuzzleFactory getFactory() {
		return new CowboyPuzzleFactory();
	}

	////////// PUZZLE ////////////

	public abstract CowboyTarget getTarget();

	public abstract CowboyBadGuy getBadGuy();

	public abstract CowboyCursorObject getCursor();

	protected void createTarget() {
		CowboyTarget target = getTarget();
		target.setX(getCol(6));
		target.setY(getCol(3));

		LAYER.PUZZLE.addObject(target);
	}

	protected void createBadGuy() {
		CowboyBadGuy badGuy = getBadGuy();
		badGuy.setX(getCol(11));
		badGuy.setY(getCol(2));

		LAYER.PUZZLE.addObject(badGuy);
	}

	protected void createCursor() {
		CowboyCursorObject cursor = getCursor();
		cursor.setX(getCol(1));
		cursor.setY(getCol(3));

		LAYER.PUZZLE.addObject(cursor);
	}

	////////// TEXTURE ////////////

	@Override
	public int getSheetColCriterion() {
		return 5;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		Color cyan = new Color(50, 175, 185, 240);
		new TransluantLayer().drawPuzzle(g, cyan);
	}

}
