package com.sunsigne.reversedrebecca.puzzle.cookie;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.object.puzzle.cookie.CookieCounterObject;
import com.sunsigne.reversedrebecca.object.puzzle.cookie.CookieObject;
import com.sunsigne.reversedrebecca.object.puzzle.cookie.CookieTitleObject;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListenerBoolean;
import com.sunsigne.reversedrebecca.pattern.render.TransluantLayer;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.PuzzleFactory;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.system.mainloop.Game;

public abstract class CookiePuzzle extends Puzzle {

	public CookiePuzzle(ToolPlayer toolPlayer, GenericListenerBoolean actionOnWinning, GenericListener actionOnLosing) {
		super(toolPlayer, actionOnWinning, actionOnLosing);

		new SoundTask().playMusic("cookie_cursor", false, true);
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

	public int getDelayBeforeReady() {
		return Game.SEC * 5;
	}
	
	protected void createCookie() {
		int x = getCol(5) + 16;
		int y = getRow(2) + 32;

		PuzzleObject cookie = new CookieObject(this, x, y);
		LAYER.PUZZLE.addObject(cookie);
	}

	private CookieCounterObject counter;

	public CookieCounterObject getCounter() {
		return counter;
	}

	protected void createCounter(int maxCount) {
		int x = getCol(5) + 16;
		int y = getRow(0) - 64;

		counter = new CookieCounterObject(this, maxCount, x, y);
		LAYER.PUZZLE.addObject(counter);
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

	////////// OPEN ////////////

	public void openPuzzle() {
		super.openPuzzle();
		PuzzleObject title = new CookieTitleObject(this);
		LAYER.PUZZLE.addObject(title);
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

	private BufferedImage image;

	public BufferedImage getImage() {
		if (image == null)
			image = new ImageTask().loadImage("textures/puzzle/" + getName() + "_title");
		return image;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		Color cyan = new Color(95, 155, 170, 240);
		new TransluantLayer().drawPuzzle(g, cyan);
	}

}
