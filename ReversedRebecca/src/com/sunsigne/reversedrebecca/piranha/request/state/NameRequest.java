package com.sunsigne.reversedrebecca.piranha.request.state;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.piranha.request.conditional.ConditionalRequest;

public class NameRequest extends ConditionalRequest {

	////////// REQUEST ////////////

	public NameRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request action = new NameRequest();

	@Override
	public Request getRequest() {
		return action;
	}

	@Override
	public String getType() {
		return "NAME";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	// WARNING ! Each name should be different in a same folder
	@Override
	public void doClassicAction(PiranhaObject object, String target) {
		object.setName(target);
	}

	@Override
	protected String getConditionToCheck(PiranhaObject object) {
		return object.getName();
	}

}
