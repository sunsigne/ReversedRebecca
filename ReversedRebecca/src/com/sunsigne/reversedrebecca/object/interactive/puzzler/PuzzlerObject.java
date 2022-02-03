package com.sunsigne.reversedrebecca.object.interactive.puzzler;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;
import com.sunsigne.reversedrebecca.object.interactive.InteractiveControlObject;
import com.sunsigne.reversedrebecca.ressources.DIFFICULTY;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;

public abstract class PuzzlerObject extends InteractiveControlObject implements CollisionReactor {

	public PuzzlerObject(DIFFICULTY difficulty, int x, int y) {
		super(x, y);
		this.difficulty = difficulty;
		loadImage();
	}

	////////// NAME ////////////

	public abstract String getName();

	////////// DIFFICULTY ////////////

	private DIFFICULTY difficulty;

	public DIFFICULTY getDifficulty() {
		return difficulty;
	}

	////////// TEXTURE ////////////

	private BufferedImage image;

	private void loadImage() {
		image = new ImageTask().loadImage("textures/" + getName() + "/" + getName() + "_" + difficulty.getName() + ".png");
	}

	public BufferedImage getImage() {
		return image;
	}

	////////// COLLISION ////////////

	@Override
	public void collidingReaction(CollisionDetector detectorObject) {
		blockPass(detectorObject);
	}

}
