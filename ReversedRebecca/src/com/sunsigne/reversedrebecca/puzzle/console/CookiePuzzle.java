package com.sunsigne.reversedrebecca.puzzle.console;

import java.awt.Color;
import java.awt.Graphics;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListenerBoolean;
import com.sunsigne.reversedrebecca.pattern.render.TransluantLayer;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.PuzzleFactory;

public abstract class CookiePuzzle extends Puzzle {

	public CookiePuzzle(ToolPlayer toolPlayer, GenericListenerBoolean actionOnWinning, GenericListener actionOnLosing) {
		super(toolPlayer, actionOnWinning, actionOnLosing);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "cookie";
	}

	////////// FACTORY ////////////

	@Override
	public PuzzleFactory getFactory() {
		return new CookiePuzzleFactory();
	}

	////////// PUZZLE ////////////

	protected void createCookie() {

	}

	////////// TOOL ////////////

	private static int noCritCount;

	@Override
	protected int getStaticNoCritCount() {
		return noCritCount;
	}

	@Override
	protected void setStaticNoCritCount(int noCritCount) {
		CookiePuzzle.noCritCount = noCritCount;
	}

	@Override
	public boolean hasCritToken() {
		return false;
	}

	////////// TICK ////////////

	@Override
	public void tick() {

	}

	////////// TEXTURE ////////////

	@Override
	public int getSheetColCriterion() {
		return 7;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		Color cyan = new Color(95, 155, 170, 240);
		new TransluantLayer().drawPuzzle(g, cyan);
	}

}
