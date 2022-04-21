package com.sunsigne.reversedrebecca.object.piranha.living.animation;

import com.sunsigne.reversedrebecca.object.piranha.living.LivingObject;

public class WalkingAnimation extends LivingAnimation {

	public WalkingAnimation(LivingObject living) {
		super(living);
	}

	////////// NAME ////////////

	public String getName() {
		return "walking";
	}

	////////// TICK ////////////

	public int getAnimationTime() {
		return 15;
	}

}