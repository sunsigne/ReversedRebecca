package com.sunsigne.reversedrebecca.object.piranha.living.animation;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.piranha.living.LivingObject;

public class StandingAnimation extends LivingAnimation {

	public StandingAnimation(LivingObject living) {
		super(living);
	}

	////////// NAME ////////////

	public String getName() {
		return "standing";
	}

	////////// TICK ////////////

	public int getAnimationTime() {
		return 60;
	}

	////////// TEXTURE ////////////

	@Override
	protected BufferedImage[] loadAnimation(DIRECTION direction) {

		String path = getName() + "_" + direction.getName();
		BufferedImage img0 = loadImage(path);

		return new BufferedImage[] { img0 };
	}

}