package com.sunsigne.reversedrebecca.object.puzzler;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.Interactive;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardController;

public abstract class PuzzlerObject extends GameObject implements Difficulty, Interactive, CollisionReactor {

	public PuzzlerObject(int x, int y) {
		this(LVL.NULL, x, y);
	}
	
	public PuzzlerObject(LVL difficulty, int x, int y) {
		super(x, y);
		this.difficulty = difficulty;
		loadImage();
	}

	////////// NAME ////////////

	public abstract String getName();

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
	
	////////// TICK ////////////

	@Override
	public void tick() {

	}

	////////// TEXTURE ////////////

	private BufferedImage image;

	private void loadImage() {
		image = new ImageTask().loadImage("textures/puzzler/" + getName() + "_" + getDifficulty().getName());
	}

	public BufferedImage getImage() {
		return image;
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
