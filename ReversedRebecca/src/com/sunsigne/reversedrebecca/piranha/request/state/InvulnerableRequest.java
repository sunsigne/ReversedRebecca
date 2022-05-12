package com.sunsigne.reversedrebecca.piranha.request.state;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.object.piranha.living.characteristics.Health;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.piranha.request.conditional.ConditionalRequest;

public class InvulnerableRequest extends ConditionalRequest {

	////////// REQUEST ////////////

	public InvulnerableRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request request = new InvulnerableRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "INVULNERABLE";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	@Override
	public void doClassicAction(PiranhaObject object, String target) {
		if (object instanceof Health == false)
			return;

		Health health = (Health) object;
		boolean invulnerable = Boolean.parseBoolean(target);
		health.setInvulnerable(invulnerable);
	}

	@Override
	protected String getConditionToCheck(PiranhaObject object) {
		if (object instanceof Health == false)
			return "true";

		Health health = (Health) object;
		return String.valueOf(health.isInvulnerable());
	}

}
