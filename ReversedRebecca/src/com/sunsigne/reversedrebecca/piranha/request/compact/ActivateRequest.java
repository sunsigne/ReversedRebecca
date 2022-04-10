package com.sunsigne.reversedrebecca.piranha.request.compact;

import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.piranha.RequestList;
import com.sunsigne.reversedrebecca.piranha.condition.ActivateCondition;
import com.sunsigne.reversedrebecca.piranha.request.Request;

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
