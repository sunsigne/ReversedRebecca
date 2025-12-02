package com.sunsigne.reversedrebecca.object.puzzle.hack;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;

public class VirusShrinkingAnimationObject extends VirusDisguiseAnimationObject {

	public VirusShrinkingAnimationObject(Puzzle puzzle, int x, int y, boolean critical, int shrink) {
		super(puzzle, x, y, critical);
		growing = 100 - (int) (2.5f * shrink);
		growingSpeed = 4 - (int) (shrink / 18);
		alpha = 0.6f;
	}

	////////// ANTIVIRUS ////////////

	@Override
	public boolean isDisguised() {
		return false;
	}

	@Override
	protected void updateMouse() {

	}

	////////// TICK ////////////

	private int growingSpeed;

	@Override
	public void tick() {
		growing = growing - growingSpeed;

		if (growing <= -getSize() / 2)
			removeObject();
	}

	////////// TEXTURE ////////////

	private BufferedImage image;

	@Override
	public BufferedImage getImage() {
		if (image == null)
			image = new ImageTask().loadImage("textures/puzzle/" + "hack_antivirus_shrinker");
		return image;
	}

}
