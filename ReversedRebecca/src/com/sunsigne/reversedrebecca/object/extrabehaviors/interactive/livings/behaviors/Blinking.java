package com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.behaviors;

import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.TickBehavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.LivingObject;

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

	public boolean isLivingHidden() {
		return time % 4 == 0;
	}

	@Override
	public void tick() {
		time--;

		living.getHandler().setHideRendering(false);

		if (isLivingHidden())
			living.setInvisible(true);
		else
			living.setInvisible(false);

		if (time <= 0) {
			living.setInvisible(false);
			living.removeBehavior(this);
		}
	}

}
