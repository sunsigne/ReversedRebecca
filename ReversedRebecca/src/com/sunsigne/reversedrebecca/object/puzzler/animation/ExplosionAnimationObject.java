package com.sunsigne.reversedrebecca.object.puzzler.animation;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.ressources.images.Animation;
import com.sunsigne.reversedrebecca.system.Size;

public class ExplosionAnimationObject extends PuzzlerAnimationObject {

	public ExplosionAnimationObject(int x, int y) {
		this(x, y, Size.M, Size.M);
	}

	public ExplosionAnimationObject(int x, int y, int w, int h) {
		super(x - w / 2, y - h / 2, w, h);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "explosion";
	}

	////////// TICK ////////////

	@Override
	public FRAME_RATE getFrameRate() {
		return FRAME_RATE.NORMAL;
	}

	////////// TEXTURE ////////////
	
	protected Animation buildAnimation(BufferedImage image) {
		return new Animation(image, 32, 32);
	}
	
}
