package com.sunsigne.reversedrebecca.piranha.request.conditional;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class StunnedRequest extends ConditionalPatternRequest {

	////////// REQUEST ////////////

	public StunnedRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request request = new StunnedRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "STUNNED";
	}

	@Override
	protected boolean finalVerification(PiranhaObject object, PiranhaObject targetObject) {
		return targetObject.isStunned();
	}

}
