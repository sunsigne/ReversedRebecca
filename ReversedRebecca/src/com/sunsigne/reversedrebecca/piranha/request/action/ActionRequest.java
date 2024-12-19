package com.sunsigne.reversedrebecca.piranha.request.action;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class ActionRequest implements Request {

	////////// REQUEST ////////////

	public ActionRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request request = new ActionRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "ACTION";
	}

	@Override
	public boolean hasCompactWriting() {
		return false;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {
		int index = Integer.parseInt(target.split(",")[0]);
		String formatedTarget = target.split(",")[1];

		Request instruction = getActionRequest(index);
		if (instruction != null)
			instruction.doAction(object, formatedTarget);

	}

	private Request getActionRequest(int index) {
		switch (index) {
		case 1:
			return new ActionOneRequest();
		case 2:
			return new ActionTwoRequest();
		case 3:
			return new ActionThreeRequest();
		}
		return null;
	}

}
