package com.sunsigne.reversedrebecca.piranha.request.gotoo;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
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
	public void doAction(PiranhaObject object, String target) {
		if (target.equalsIgnoreCase("null"))
			return;

		// global case
		boolean restricted = target.contains("*");
		if (restricted == false) {
			new AffectingCondition().registerValue(target);
			return;
		}

		// restricted case
		String name = String.valueOf(target.split("\\*")[0]);
		String value = String.valueOf(target.split("\\*")[1]);
		new AffectingCondition().registerValue(name, value);
	}

}
