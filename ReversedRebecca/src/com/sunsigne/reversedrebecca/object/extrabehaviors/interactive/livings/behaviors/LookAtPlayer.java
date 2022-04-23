package com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.behaviors;

import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.TickBehavior;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.piranha.request.other.FacingRequest;

public class LookAtPlayer implements TickBehavior {

	public LookAtPlayer(/*LivingObject living*/) {
//		this.living = living;
	}

	////////// BEHAVIOR ////////////

//	private LivingObject living;

	@Override
	public ExtraBehaviorsObject getExtraBehaviorsObject() {
		return null;
	}

	////////// TICK ////////////

	@Override
	public void tick() {
//		Request request = RequestList.getList().getObject(new FacingRequest());
//		request.doAction(living, "player");
	}

}
