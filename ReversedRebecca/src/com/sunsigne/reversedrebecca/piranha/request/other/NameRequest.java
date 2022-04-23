package com.sunsigne.reversedrebecca.piranha.request.other;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class NameRequest implements Request {

	////////// REQUEST ////////////

	public NameRequest() {
		RequestList.getList().addObject(this);
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
	public void doAction(PiranhaObject object, String target) {
		object.setName(target);
	}

}
