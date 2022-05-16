package com.sunsigne.reversedrebecca.object.piranha.living.animation.constant.fixed;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.piranha.living.LivingObject;
import com.sunsigne.reversedrebecca.object.piranha.living.animation.constant.ConstantAnimation;

public abstract class FixedAnimation extends ConstantAnimation {

	public FixedAnimation(LivingObject living) {
		super(living);
	}

	////////// TEXTURE ////////////

	@Override
	protected BufferedImage[] loadAnimation(DIRECTION direction) {

		String path = getName();
		BufferedImage img0 = loadImage("fixed_" + path);

		return new BufferedImage[] { img0 };
	}

}