package com.sunsigne.reversedrebecca.object.animation;

import com.sunsigne.reversedrebecca.system.Size;

public class OpenChestAnimationObject extends AnimationObject {

	public OpenChestAnimationObject(int x, int y) {
		super(x, y);
		setY(getY() - Size.XS);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "chest_open";
	}

	////////// TICK ////////////

	@Override
	public FRAME_RATE getFrameRate() {
		return FRAME_RATE.NORMAL;
	}

}
