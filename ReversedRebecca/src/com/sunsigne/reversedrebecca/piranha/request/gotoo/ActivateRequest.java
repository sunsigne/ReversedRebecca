package com.sunsigne.reversedrebecca.piranha.request.gotoo;

import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.piranha.condition.global.ActivateCondition;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class ActivateRequest implements Request {

	////////// REQUEST ////////////

	public ActivateRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request request = new ActivateRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "ACTIVATE";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	@Override
	public void doAction(ExtraBehaviorsObject object, String target) {

		new ActivateCondition().registerValue(target);
	}

}
