package com.sunsigne.reversedrebecca.piranha.request.conditional;

import com.sunsigne.reversedrebecca.object.characteristics.interactive.ActionOption;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.ActionOption.ACTION_DESIGN;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class NumberSettingsRequest extends ConditionalRequest {

	////////// REQUEST ////////////

	public NumberSettingsRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request request = new NumberSettingsRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "NUMBER_SETTINGS";
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
		return ActionOption.getDesign() == ACTION_DESIGN.NUMBER;
	}

}
