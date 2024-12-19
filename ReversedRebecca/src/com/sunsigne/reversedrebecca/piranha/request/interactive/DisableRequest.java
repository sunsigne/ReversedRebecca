package com.sunsigne.reversedrebecca.piranha.request.interactive;

import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class DisableRequest extends EnableRequest {

	////////// REQUEST ////////////

	public DisableRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request action = new DisableRequest();

	@Override
	public Request getRequest() {
		return action;
	}

	@Override
	public String getType() {
		return "DISABLE";
	}

	@Override
	public boolean disable() {
		return true;
	}

}
