package com.sunsigne.reversedrebecca.object.piranha.living.animation;

import com.sunsigne.reversedrebecca.object.piranha.living.LivingObject;

public class SickAnimation extends LivingAnimation {

	public SickAnimation(LivingObject living) {
		super(living);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "sick";
	}

	////////// TICK ////////////

	@Override
	public int getAnimationTime() {
		return 30;
	}

}