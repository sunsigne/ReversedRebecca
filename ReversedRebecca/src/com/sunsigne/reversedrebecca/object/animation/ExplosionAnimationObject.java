package com.sunsigne.reversedrebecca.object.animation;

import com.sunsigne.reversedrebecca.system.Size;

public class ExplosionAnimationObject extends AnimationObject {

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

}
