package com.sunsigne.reversedrebecca.piranha.request.conditional;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class PlayerFutherThanRequest extends ConditionalRequest {

	////////// REQUEST ////////////

	public PlayerFutherThanRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request request = new PlayerFutherThanRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "PLAYER_FUTHER_THAN";
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
		return new PlayerFinder().isPlayerFutherThan(object, valueToCheck);
	}

}
