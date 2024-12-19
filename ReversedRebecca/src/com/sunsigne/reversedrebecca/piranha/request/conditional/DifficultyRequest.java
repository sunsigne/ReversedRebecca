package com.sunsigne.reversedrebecca.piranha.request.conditional;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.system.DifficultyOption;

public class DifficultyRequest extends ConditionalRequest {

	////////// REQUEST ////////////

	public DifficultyRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request request = new DifficultyRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "DIFFICULTY";
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
		String valueToCheck = target.split("\\?")[0];
		return DifficultyOption.getDifficulty().getName().equalsIgnoreCase(valueToCheck);
	}

}
