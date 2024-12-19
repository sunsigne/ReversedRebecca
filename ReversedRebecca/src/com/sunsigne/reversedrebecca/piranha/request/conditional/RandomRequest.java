package com.sunsigne.reversedrebecca.piranha.request.conditional;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class RandomRequest extends ConditionalRequest {

	////////// REQUEST ////////////

	public RandomRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request request = new RandomRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "RANDOM";
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
		String condition = String.valueOf(target.split("\\?")[0]);
		String conditionType = String.valueOf(condition.split(":")[0]);
		String valueToCheck = String.valueOf(condition.split(":")[1]);

		return analyseCondition(conditionType, valueToCheck);
	}

	private boolean analyseCondition(String conditionType, String valueToCheck) {
		switch (conditionType) {

		case "PERCENTAGE":
			return new RandomGenerator().getBoolean(Integer.parseInt(valueToCheck));
		}

		return false;
	}

}
