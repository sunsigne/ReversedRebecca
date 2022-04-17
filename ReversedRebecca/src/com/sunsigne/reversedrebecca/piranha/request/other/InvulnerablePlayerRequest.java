package com.sunsigne.reversedrebecca.piranha.request.other;

import com.sunsigne.reversedrebecca.characteristics.PlayerHealth;
import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class InvulnerablePlayerRequest implements Request {

	////////// REQUEST ////////////

	public InvulnerablePlayerRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request request = new InvulnerablePlayerRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "PLAYER_INVULNERABLE";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	@Override
	public void doAction(ExtraBehaviorsObject object, String target) {
		boolean invulnerable = Boolean.parseBoolean(target);
		PlayerHealth.getInstance().setInvulnerable(invulnerable);
	}

}
