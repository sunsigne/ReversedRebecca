package com.sunsigne.reversedrebecca.piranha.request.conditional;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.world.World;

public class KarmaSmallerThanRequest extends ConditionalRequest {

	////////// REQUEST ////////////

	public KarmaSmallerThanRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request request = new KarmaSmallerThanRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "KARMA_SMALLER_THAN";
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
		return World.get().getLevelStats().getKarma().getValue() < valueToCheck;
	}

}
