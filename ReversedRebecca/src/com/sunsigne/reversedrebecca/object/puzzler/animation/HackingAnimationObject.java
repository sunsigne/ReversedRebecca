package com.sunsigne.reversedrebecca.object.puzzler.animation;

import com.sunsigne.reversedrebecca.system.Size;

public class HackingAnimationObject extends PuzzlerAnimationObject {

	public HackingAnimationObject(int x, int y) {
		super(x - Size.XS / 4, y - Size.M / 4, Size.XS / 2, Size.XS / 2);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "computer_crash";
	}

	////////// TICK ////////////

	@Override
	public FRAME_RATE getFrameRate() {
		return FRAME_RATE.FAST;
	}

}
