package com.sunsigne.reversedrebecca.piranha.request.conditional;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class PlayerCloserThanRequest extends ConditionalRequest {

	////////// REQUEST ////////////

	public PlayerCloserThanRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request request = new PlayerCloserThanRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "PLAYER_CLOSER_THAN";
	}

	@Override
	public boolean hasCompactWriting() {
		return false;
	}

	@Override
	public void doClassicAction(PiranhaObject object, String target) {

	}

	@Override
	protected String getConditionToCheck(PiranhaObject object) {
		return null;
	}

	@Override
	protected boolean analyseCondition(PiranhaObject object, String target) {
		int valueToCheck = Integer.parseInt(target.split("\\?")[0]);
		return new PlayerFinder().isPlayerCloserThan(object, valueToCheck);
	}

}
