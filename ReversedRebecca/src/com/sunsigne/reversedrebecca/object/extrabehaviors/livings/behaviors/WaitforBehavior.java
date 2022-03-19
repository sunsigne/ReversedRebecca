package com.sunsigne.reversedrebecca.object.extrabehaviors.livings.behaviors;

import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.TickBehavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.LivingObject;
import com.sunsigne.reversedrebecca.pattern.ConditionalListener;

public class WaitforBehavior implements TickBehavior {

	public WaitforBehavior(LivingObject living, ConditionalListener listener) {
		this.living = living;
		this.listener = listener;
	}

	////////// BEHAVIOR ////////////

	private LivingObject living;

	@Override
	public LivingObject getExtraBehaviorsObject() {
		return living;
	}

	////////// TICK ////////////

	private ConditionalListener listener;

	@Override
	public void tick() {
		if (!listener.canDoAction())
			return;

		living.getBehaviorList().removeObject(this);
		listener.doAction();
	}

}
