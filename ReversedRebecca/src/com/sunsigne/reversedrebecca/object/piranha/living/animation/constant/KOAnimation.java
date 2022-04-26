package com.sunsigne.reversedrebecca.object.piranha.living.animation.constant;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.piranha.living.LivingObject;

public class KOAnimation extends ConstantAnimation {

	public KOAnimation(LivingObject living) {
		super(living);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "ko";
	}

	////////// TEXTURE ////////////

	private static boolean erreurAlreadyDisplayed;

	@Override
	protected BufferedImage[] loadAnimation(DIRECTION direction) {

		if (!erreurAlreadyDisplayed) {
			System.err.println("ko animation in only 1 direction");
			erreurAlreadyDisplayed = true;
		}

		String path = getName();
		BufferedImage img0 = loadImage(path);

		return new BufferedImage[] { img0 };
	}

}