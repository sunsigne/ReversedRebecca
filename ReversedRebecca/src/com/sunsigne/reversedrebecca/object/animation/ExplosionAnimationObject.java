package com.sunsigne.reversedrebecca.object.animation;

import com.sunsigne.reversedrebecca.system.Size;

public class ExplosionAnimationObject extends AnimationObject {

	public ExplosionAnimationObject(int x, int y, boolean isCritical) {
		this(x, y, Size.M, Size.M, isCritical);
	}

	public ExplosionAnimationObject(int x, int y, int w, int h, boolean isCritical) {
		super(x - w / 2, y - h / 2, w, h, isCritical);
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

}
