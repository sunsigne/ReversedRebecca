package com.sunsigne.reversedrebecca.object.puzzler;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.Interactive;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardController;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;

public abstract class PuzzlerObject extends GameObject implements TickFree, Difficulty, Interactive, CollisionReactor {

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

	private boolean isDisabled;

	@Override
	public boolean isDisabled() {
		return isDisabled;
	}

	@Override
	public void setDisabled(boolean isDisabled) {
		this.isDisabled = isDisabled;
	}

	protected abstract void loadTripleAction();

	////////// TEXTURE ////////////

	private BufferedImage image;

	public BufferedImage getImage() {
		if (image == null)
			image = new ImageTask().loadImage("textures/puzzler/" + getName() + "_" + getDifficulty().getName());
		return image;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
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

}
