package com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.behaviors.render;

import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.LivingObject;

public class SickRender extends LivingRender {

	public SickRender(LivingObject living) {
		super(living);

	}

	////////// NAME ////////////

	public String getName() {
		return "sick";
	}

	////////// TICK ////////////

	public int getAnimationTime() {
		return 30;
	}

}