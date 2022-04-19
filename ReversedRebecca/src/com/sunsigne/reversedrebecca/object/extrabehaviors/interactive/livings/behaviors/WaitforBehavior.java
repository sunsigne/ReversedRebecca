package com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.behaviors;

import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.TickBehavior;
import com.sunsigne.reversedrebecca.pattern.ConditionalListener;

public class WaitforBehavior implements TickBehavior {

	public WaitforBehavior(ExtraBehaviorsObject object, ConditionalListener listener) {
		if (listener == null) {
			object.getBehaviorList().removeObject(this);
			return;
		}

		this.object = object;
		this.listener = listener;
	}

	////////// BEHAVIOR ////////////

	private ExtraBehaviorsObject object;

	@Override
	public ExtraBehaviorsObject getExtraBehaviorsObject() {
		return object;
	}

	////////// TICK ////////////

	private ConditionalListener listener;

	@Override
	public void tick() {
		if (!listener.canDoAction())
			return;

		object.getBehaviorList().removeObject(this);
		listener.doAction();
	}

}
