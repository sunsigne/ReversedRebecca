package com.sunsigne.reversedrebecca.object.piranha.living.animation.constant;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.piranha.living.LivingObject;
import com.sunsigne.reversedrebecca.object.piranha.living.animation.LivingAnimation;

public abstract class ConstantAnimation extends LivingAnimation {

	public ConstantAnimation(LivingObject living) {
		super(living);
	}

	////////// TICK ////////////

	@Override
	public int getAnimationTime() {
		return -1;
	}

	@Override
	public void freeze() {

	}

	////////// TEXTURE ////////////

	@Override
	protected BufferedImage[] loadAnimation(DIRECTION direction) {

		String path = getName() + "_" + direction.getName();
		BufferedImage img0 = loadImage(path);

		return new BufferedImage[] { img0 };
	}

}
