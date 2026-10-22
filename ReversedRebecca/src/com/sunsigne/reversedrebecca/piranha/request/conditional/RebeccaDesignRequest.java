package com.sunsigne.reversedrebecca.piranha.request.conditional;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.object.piranha.living.player.RebeccaDesign;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class RebeccaDesignRequest extends ConditionalRequest {

	////////// REQUEST ////////////

	public RebeccaDesignRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request request = new RebeccaDesignRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "REBECCA_DESIGN";
	}

	@Override
	public boolean hasCompactWriting() {
		return false;
	}

	@Override
	public void doClassicAction(PiranhaObject object, String target) {

	}

	protected String getConditionToCheck(PiranhaObject object) {
		return RebeccaDesign.getType().getName();
	}

}
