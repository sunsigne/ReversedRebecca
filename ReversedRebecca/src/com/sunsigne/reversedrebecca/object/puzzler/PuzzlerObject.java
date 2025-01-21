package com.sunsigne.reversedrebecca.object.puzzler;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty;
import com.sunsigne.reversedrebecca.object.characteristics.Highlightable;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.Interactive;
import com.sunsigne.reversedrebecca.pattern.GameTimer;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.ressources.images.SheetableImage;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadController;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardController;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;

public abstract class PuzzlerObject extends GameObject
		implements TickFree, SheetableImage, Difficulty, Interactive, CollisionReactor {

	// you should NOT use it : this exist for test purposes only
	public PuzzlerObject(DEV_LVL devDifficulty, int x, int y) {
		super(x, y);
		this.devDifficulty = devDifficulty;
		this.difficulty = devDifficulty.isEasy() ? LVL.CYAN : LVL.RED;
		init();
	}

	public PuzzlerObject(LVL difficulty, int x, int y) {
		super(x, y);
		this.devDifficulty = null;
		this.difficulty = difficulty;
		init();
	}

	private void init() {
		loadTripleAction();
		createTextAction();
	}

	////////// NAME ////////////

	public abstract String getName();

	@Override
	public String toString() {
		var clazz = "PUZZLER";
		var name = getName().toUpperCase();
		var lvl = devDifficulty == null ? difficulty.getName().toUpperCase() : devDifficulty;
		var goal = new GoalObject(getX(), getY(), true);
		return clazz + " : " + name + " " + lvl + " : " + goal.getX() + "-" + goal.getY();
	}

	////////// DEV LVL ////////////

	private DEV_LVL devDifficulty;

	public DEV_LVL getDevDifficulty() {
		return devDifficulty;
	}

	public enum DEV_LVL {
		EASIEST, EASIER, HARDER, HARDEST;

		private boolean isEasy() {
			return this == EASIEST | this == EASIER;
		}
	}

	////////// DIFFICULTY ////////////

	private LVL difficulty;

	@Override
	public LVL getDifficulty() {
		return difficulty;
	}

	@Override
	public void setDifficulty(LVL difficulty) {
		this.difficulty = difficulty;
	}

	////////// INTERACTIVE ////////////

	private GameList<Highlightable> spreadInteractivenessList = new GameList<>(LISTTYPE.ARRAY);

	@Override
	public GameList<Highlightable> getSpreadInteractivenessList() {
		return spreadInteractivenessList;
	}

	private boolean isDisabled;

	@Override
	public boolean isDisabled() {
		return isDisabled;
	}

	@Override
	public void setDisabled(boolean isDisabled) {
		this.isDisabled = isDisabled;
	}

	private GameTimer delayer;

	@Override
	public GameTimer getDelayer() {
		return delayer;
	}

	@Override
	public void setDelayer(GameTimer delayer) {
		this.delayer = delayer;
	}

	protected abstract void loadTripleAction();

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.PUZZLER;
	}
	
	////////// TEXTURE ////////////

	protected BufferedImage image;
	protected BufferedImage highlightImage;

	@Override
	public int getSheetColCriterion() {
		return 1 + getDifficulty().ordinal();
	}

	public abstract BufferedImage getImage();

	public abstract BufferedImage getHighlightImage();

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
		drawHighlight(g, getHighlightImage());
	}

	////////// COLLISION ////////////

	@Override
	public boolean isBlockingSight() {
		return true;
	}

	@Override
	public boolean isBlockingPath() {
		return true;
	}

	@Override
	public void collidingReaction(CollisionDetector detectorObject) {
		blockPath(detectorObject);
	}

	////////// KEYBOARD ////////////

	private KeyboardController keyboardController = new KeyboardController(this);

	@Override
	public KeyboardController getKeyBoardController() {
		return keyboardController;
	}

	////////// GAMEPAD ////////////

	private GamepadController gamepadController = new GamepadController(this);

	@Override
	public GamepadController getGamepadController() {
		return gamepadController;
	}

}
