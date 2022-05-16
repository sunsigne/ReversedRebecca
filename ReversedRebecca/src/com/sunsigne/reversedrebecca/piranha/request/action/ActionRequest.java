package com.sunsigne.reversedrebecca.piranha.request.action;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class ActionRequest implements Request {

	////////// REQUEST ////////////

	public ActionRequest() {
		RequestList.getList().addObject(this);
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
			return RequestList.getList().getObject(new ActionOneRequest());
		case 2:
			return RequestList.getList().getObject(new ActionTwoRequest());
		case 3:
			return RequestList.getList().getObject(new ActionThreeRequest());
		}
		return null;
	}

}
