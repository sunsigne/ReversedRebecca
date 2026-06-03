package com.sunsigne.reversedrebecca.object.animation;

import com.sunsigne.reversedrebecca.system.Size;

public class DigAnimationObject extends AnimationObject {

	public DigAnimationObject(int x, int y, boolean isCritical) {
		super(x - Size.XS / 4, y - Size.M / 4, Size.XS / 2, Size.XS / 2, isCritical);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "hole_dug";
	}

	////////// TICK ////////////

	@Override
	public FRAME_RATE getFrameRate() {
		return FRAME_RATE.FAST;
	}

}
