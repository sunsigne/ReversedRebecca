package com.sunsigne.reversedrebecca.piranha.request.interactive;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class EnableOntheSpotRequest extends EnableRequest {

	////////// REQUEST ////////////

	public EnableOntheSpotRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request action = new EnableOntheSpotRequest();

	@Override
	public Request getRequest() {
		return action;
	}

	@Override
	public String getType() {
		return "ENABLE_ONTHESPOT";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {
		super.doAction(object, "onthespot:" + target);
	}

}
