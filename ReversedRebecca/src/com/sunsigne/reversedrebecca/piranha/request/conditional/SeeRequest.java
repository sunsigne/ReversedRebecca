package com.sunsigne.reversedrebecca.piranha.request.conditional;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.physic.finder.SightFinder;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class SeeRequest extends ConditionalPatternRequest {

	////////// REQUEST ////////////

	public SeeRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request request = new SeeRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "SEE";
	}

	@Override
	protected boolean finalVerification(PiranhaObject object, PiranhaObject targetObject) {
		return new SightFinder(object, targetObject).isGoalInSight();
	}

}
