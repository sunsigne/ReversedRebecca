package com.sunsigne.reversedrebecca.object.piranha.living.animation.constant.fixed;

import com.sunsigne.reversedrebecca.object.piranha.living.LivingObject;

public class BathAnimation extends FixedAnimation {

	public BathAnimation(LivingObject living) {
		super(living);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "bath";
	}

}