package com.sunsigne.reversedrebecca.piranha.request.interactive;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class DisableOntheSpotRequest extends DisableRequest {

	////////// REQUEST ////////////

	public DisableOntheSpotRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request action = new DisableOntheSpotRequest();

	@Override
	public Request getRequest() {
		return action;
	}

	@Override
	public String getType() {
		return "DISABLE_ONTHESPOT";
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
