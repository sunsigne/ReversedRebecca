package com.sunsigne.reversedrebecca.puzzle.cookie;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.object.puzzle.cookie.CookieCounterObject;
import com.sunsigne.reversedrebecca.object.puzzle.cookie.CookieCounting;
import com.sunsigne.reversedrebecca.object.puzzle.cookie.CookieObject;
import com.sunsigne.reversedrebecca.object.puzzle.cookie.CookieTitleObject;
import com.sunsigne.reversedrebecca.object.puzzle.cookie.upgrade.COOKIE_UPGRADE;
import com.sunsigne.reversedrebecca.object.puzzle.cookie.upgrade.CookieUpgradeAntiGObject;
import com.sunsigne.reversedrebecca.object.puzzle.cookie.upgrade.CookieUpgradeCursorObject;
import com.sunsigne.reversedrebecca.object.puzzle.cookie.upgrade.CookieUpgradeFactoryObject;
import com.sunsigne.reversedrebecca.object.puzzle.cookie.upgrade.CookieUpgradeGrandpaObject;
import com.sunsigne.reversedrebecca.object.puzzle.cookie.upgrade.CookieUpgradeObject;
import com.sunsigne.reversedrebecca.object.puzzle.cookie.upgrade.CookieUpgradeStockObject;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListenerBoolean;
import com.sunsigne.reversedrebecca.pattern.render.TransluantLayer;
import com.sunsigne.reversedrebecca.puzzle.PuzzleFactory;
import com.sunsigne.reversedrebecca.puzzle.PuzzleGamepad;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;
import com.sunsigne.reversedrebecca.system.controllers.mouse.PresetMousePos;
import com.sunsigne.reversedrebecca.system.mainloop.Game;

public abstract class CookiePuzzle extends PuzzleGamepad {

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

	private CookieUpgradeObject cursorUpgrade;
	private CookieUpgradeObject grandpaUpgrade;
	private CookieUpgradeObject factoryUpgrade;
	private CookieUpgradeObject stockUpgrade;
	private CookieUpgradeObject antiGUpgrade;

	public CookieCounting getUpgrade(COOKIE_UPGRADE type) {
		switch (type) {
		case COOKIE:
			return getCounter();
		case CURSOR:
			return cursorUpgrade;
		case GRANDPA:
			return grandpaUpgrade;
		case FACTORY:
			return factoryUpgrade;
		case STOCK:
			return stockUpgrade;
		case ANTI_G:
			return antiGUpgrade;
		}
		return null;
	}

	private static int gap = 21;

	public void createUpgrade(COOKIE_UPGRADE type, COOKIE_UPGRADE unlockingType, int unlockingAt, boolean nerfed) {
		switch (type) {
		case CURSOR:
			cursorUpgrade = new CookieUpgradeCursorObject(this, getCol(8), getRow(1) + 1 * gap, unlockingType,
					unlockingAt, nerfed);
			LAYER.PUZZLE.addObject(cursorUpgrade);
			break;
		case GRANDPA:
			grandpaUpgrade = new CookieUpgradeGrandpaObject(this, getCol(8), getRow(2) + 2 * gap, unlockingType,
					unlockingAt, nerfed);
			LAYER.PUZZLE.addObject(grandpaUpgrade);
			break;
		case FACTORY:
			factoryUpgrade = new CookieUpgradeFactoryObject(this, getCol(8), getRow(3) + 3 * gap, unlockingType,
					unlockingAt, nerfed);
			LAYER.PUZZLE.addObject(factoryUpgrade);
			break;
		case STOCK:
			stockUpgrade = new CookieUpgradeStockObject(this, getCol(8), getRow(4) + 4 * gap, unlockingType,
					unlockingAt, nerfed);
			LAYER.PUZZLE.addObject(stockUpgrade);
			break;
		case ANTI_G:
			antiGUpgrade = new CookieUpgradeAntiGObject(this, getCol(8), getRow(5) + 5 * gap, unlockingType,
					unlockingAt, nerfed);
			LAYER.PUZZLE.addObject(antiGUpgrade);
			break;
		default:
			break;
		}
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

	////////// PRESET MOUSE POS ////////////

	public static final PresetMousePos CENTER_COOKIE = new PresetMousePos(getCol(7f), getRow(4f));
	public static final PresetMousePos COOKIE = new PresetMousePos(getCol(4f), getRow(4.5f));
	public static final PresetMousePos CURSOR = new PresetMousePos(getCol(10f), getRow(1.5f) + 1 * gap);
	public static final PresetMousePos GRANDPA = new PresetMousePos(getCol(10f), getRow(2.5f) + 2 * gap);
	public static final PresetMousePos FACTORY = new PresetMousePos(getCol(10f), getRow(3.5f) + 3 * gap);
	public static final PresetMousePos STOCK = new PresetMousePos(getCol(10f), getRow(4.5f) + 4 * gap);
	public static final PresetMousePos ANTI_G = new PresetMousePos(getCol(10f), getRow(5.5f) + 5 * gap);

	@Override
	public PresetMousePos getDefaultPreset() {
		CookieCounting upgrade = getUpgrade(COOKIE_UPGRADE.CURSOR);
		if (upgrade == null)
			return CENTER_COOKIE;
		if (upgrade.isUnlocked() == false)
			return CENTER_COOKIE;

		return COOKIE;
	}

	////////// GAMEPAD ////////////

	@Override
	public void buttonPressed(ButtonEvent e) {
		if (pressingButton())
			return;

		if (isPresetNull())
			setPreset(getDefaultPreset());

		if (getPreset() == COOKIE) {
			if (e.getKey() == ButtonEvent.RIGHT && getUpgrade(COOKIE_UPGRADE.CURSOR).isUnlocked())
				setPreset(CURSOR);
		}

		else if (getPreset() == CURSOR) {
			if (e.getKey() == ButtonEvent.LEFT)
				setPreset(COOKIE);
			if (e.getKey() == ButtonEvent.DOWN && getUpgrade(COOKIE_UPGRADE.GRANDPA).isUnlocked())
				setPreset(GRANDPA);
		}

		else if (getPreset() == GRANDPA) {
			if (e.getKey() == ButtonEvent.LEFT)
				setPreset(COOKIE);
			if (e.getKey() == ButtonEvent.UP && getUpgrade(COOKIE_UPGRADE.CURSOR).isUnlocked())
				setPreset(CURSOR);
			if (e.getKey() == ButtonEvent.DOWN && getUpgrade(COOKIE_UPGRADE.FACTORY).isUnlocked())
				setPreset(FACTORY);
		}

		else if (getPreset() == FACTORY) {
			if (e.getKey() == ButtonEvent.LEFT)
				setPreset(COOKIE);
			if (e.getKey() == ButtonEvent.UP && getUpgrade(COOKIE_UPGRADE.GRANDPA).isUnlocked())
				setPreset(GRANDPA);
			if (e.getKey() == ButtonEvent.DOWN && getUpgrade(COOKIE_UPGRADE.STOCK).isUnlocked())
				setPreset(STOCK);
		}

		else if (getPreset() == STOCK) {
			if (e.getKey() == ButtonEvent.LEFT)
				setPreset(COOKIE);
			if (e.getKey() == ButtonEvent.UP && getUpgrade(COOKIE_UPGRADE.FACTORY).isUnlocked())
				setPreset(FACTORY);
			if (e.getKey() == ButtonEvent.DOWN && getUpgrade(COOKIE_UPGRADE.ANTI_G).isUnlocked())
				setPreset(ANTI_G);
		}

		else if (getPreset() == ANTI_G) {
			if (e.getKey() == ButtonEvent.LEFT)
				setPreset(COOKIE);
			if (e.getKey() == ButtonEvent.UP && getUpgrade(COOKIE_UPGRADE.STOCK).isUnlocked())
				setPreset(STOCK);
		}
	}

	@Override
	public void buttonReleased(ButtonEvent e) {

	}

}
