package com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.behaviors;

import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.TickBehavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.LivingObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.piranha.request.compact.FacingRequest;

public class LookAtPlayer implements TickBehavior {

	public LookAtPlayer(LivingObject living) {
		this.living = living;
	}

	////////// BEHAVIOR ////////////

	private LivingObject living;

	@Override
	public LivingObject getExtraBehaviorsObject() {
		return living;
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		Request request = RequestList.getList().getObject(new FacingRequest());
		request.doAction(living, "player");
	}

}
