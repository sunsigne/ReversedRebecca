package com.sunsigne.reversedrebecca.puzzle.cowboy;

import java.awt.Color;
import java.awt.Graphics;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.puzzle.cowboy.CowboyCursorObject;
import com.sunsigne.reversedrebecca.object.puzzle.cowboy.CowboyHoleObject;
import com.sunsigne.reversedrebecca.object.puzzle.cowboy.CowboyTargetObject;
import com.sunsigne.reversedrebecca.object.puzzle.cowboy.living.CowboyBadGuyObject;
import com.sunsigne.reversedrebecca.object.puzzle.cowboy.living.CowboyRebeccaObject;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListenerBoolean;
import com.sunsigne.reversedrebecca.pattern.render.TransluantLayer;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.PuzzleFactory;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor;
import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor.CURSOR_TYPE;

public abstract class CowboyPuzzle extends Puzzle {

	public CowboyPuzzle(ToolPlayer toolPlayer, GenericListenerBoolean actionOnWinning, GenericListener actionOnLosing) {
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

	protected void createTarget() {
		CowboyTargetObject target = new CowboyTargetObject(this, isCritical);
		target.setX(getCol(4) + Size.S);
		target.setY(getCol(1));
		LAYER.PUZZLE.addObject(target);
	}

	private CowboyRebeccaObject rebecca;

	protected void createRebecca() {
		rebecca = new CowboyRebeccaObject(this, isCritical);
		rebecca.setX(0);
		rebecca.setY(getCol(3));
		LAYER.PUZZLE.addObject(rebecca);
	}

	private CowboyBadGuyObject badGuy;

	protected void createBadGuy() {
		badGuy = new CowboyBadGuyObject(this, isCritical);
		badGuy.setX(getCol(10));
		badGuy.setY(getCol(3));
		LAYER.PUZZLE.addObject(badGuy);
	}

	protected void createCursor() {
		CowboyCursorObject cursor = new CowboyCursorObject(this, isCritical);
		cursor.setX(getCol(1));
		cursor.setY(getCol(3));
		LAYER.PUZZLE.addObject(cursor);
	}

	private CowboyHoleObject hole;

	public void createHoleObject(int x, int y) {
		rebecca.cycle();
		badGuy.cycle();
		hole = new CowboyHoleObject(this, isCritical, x, y);
		LAYER.PUZZLE.addObject(hole);
	}

	private boolean winning;

	public boolean isWinning() {
		return winning;
	}

	public void setWinning(boolean winning) {
		badGuy.cycle();
		rebecca.cycle();
		rebecca.cycle();
		rebecca.cycle();
		rebecca.cycle();
		this.winning = winning;
	}

	////////// TOOL ////////////

	private static int noCritCount;

	@Override
	protected int getStaticNoCritCount() {
		return noCritCount;
	}

	@Override
	protected void setStaticNoCritCount(int noCritCount) {
		CowboyPuzzle.noCritCount = noCritCount;
	}
	
	@Override
	public boolean hasCritToken() {
		return false;
	}
	
	////////// TICK ////////////

	private int time = 150;

	@Override
	public void tick() {
		if (hole == null)
			return;

		time--;

		if (time <= 0)
			closePuzzle(winning);
	}

	////////// TEXTURE ////////////

	@Override
	public int getSheetColCriterion() {
		return 6;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		Color cyan = new Color(215, 150, 50, 240);
		new TransluantLayer().drawPuzzle(g, cyan);
	}

}
