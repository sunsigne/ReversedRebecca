package com.sunsigne.reversedrebecca.object.interactive.puzzler;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty;
import com.sunsigne.reversedrebecca.object.interactive.InteractiveControlObject;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;

public abstract class PuzzlerObject extends InteractiveControlObject implements Difficulty, CollisionReactor {

	public PuzzlerObject(LVL difficulty, int x, int y) {
		super(difficulty, x, y);
		loadImage();
	}

	////////// NAME ////////////

	public abstract String getName();

	////////// TEXTURE ////////////

	private BufferedImage image;

	private void loadImage() {
		image = new ImageTask().loadImage("textures/" + getName() + "/" + getName() + "_" + getDifficulty().getName());
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
