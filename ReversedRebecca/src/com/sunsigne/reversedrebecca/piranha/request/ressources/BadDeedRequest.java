package com.sunsigne.reversedrebecca.piranha.request.ressources;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class BadDeedRequest extends DeedRequest {

	////////// REQUEST ////////////

	public BadDeedRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request request = new BadDeedRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "BAD_DEED";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {
		super.doAction(object, "BAD," + target);
	}

}
