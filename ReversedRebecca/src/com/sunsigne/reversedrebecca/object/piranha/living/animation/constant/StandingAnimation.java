package com.sunsigne.reversedrebecca.object.piranha.living.animation.constant;

import com.sunsigne.reversedrebecca.object.piranha.living.LivingObject;

public class StandingAnimation extends ConstantAnimation {

	public StandingAnimation(LivingObject living) {
		super(living);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "standing";
	}

}