package com.sunsigne.reversedrebecca.piranha.request.gotoo;

import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.piranha.condition.global.AffectingCondition;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class AffectingRequest implements Request {

	////////// REQUEST ////////////

	public AffectingRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request request = new AffectingRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "AFFECTING";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	@Override
	public void doAction(ExtraBehaviorsObject object, String target) {

		new AffectingCondition().registerValue(target);
	}

}
