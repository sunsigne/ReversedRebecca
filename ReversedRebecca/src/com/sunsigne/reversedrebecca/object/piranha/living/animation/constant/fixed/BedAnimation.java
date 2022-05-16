package com.sunsigne.reversedrebecca.object.piranha.living.animation.constant.fixed;

import com.sunsigne.reversedrebecca.object.piranha.living.LivingObject;

public class BedAnimation extends FixedAnimation {

	public BedAnimation(LivingObject living) {
		super(living);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "bed";
	}

}