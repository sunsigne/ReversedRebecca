package com.sunsigne.reversedrebecca.piranha.request.conditional;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.system.controllers.ControllerManager;

public class GamepadRequest extends ConditionalRequest {

	////////// REQUEST ////////////

	public GamepadRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request request = new GamepadRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "GAMEPAD";
	}

	@Override
	public boolean hasCompactWriting() {
		return false;
	}

	@Override
	public void doClassicAction(PiranhaObject object, String target) {

	}

	@Override
	protected String getConditionToCheck(PiranhaObject object) {
		return null;
	}

	@Override
	protected boolean analyseCondition(PiranhaObject object, String target) {
		boolean valueToCheck = Boolean.parseBoolean(String.valueOf(target.split("\\?")[0]));
		boolean isUsingGamepad = ControllerManager.getInstance().isUsingGamepad();
		return valueToCheck == isUsingGamepad;
	}

}
