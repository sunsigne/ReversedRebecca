package com.sunsigne.reversedrebecca.object.extrabehaviors.livings.behaviors;

import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.TickBehavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.LivingObject;

public class Blinking implements TickBehavior {

	public Blinking(LivingObject living, int time) {
		this.living = living;
		this.time = time;
	}

	////////// BEHAVIOR ////////////

	private LivingObject living;

	@Override
	public LivingObject getExtraBehaviorsObject() {
		return living;
	}

	////////// TICK ////////////

	private int time;

	@Override
	public void tick() {
		time--;

		living.getHandler().setHideRendering(false);
		if (time % 4 == 0)
			living.getHandler().setHideRendering(true);

		if (time <= 0) {
			living.getHandler().setHideRendering(false);
			living.removeBehavior(this);
		}
	}

}
