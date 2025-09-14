package com.sunsigne.reversedrebecca.piranha.request.memory;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.piranha.request.conditional.ConditionalRequest;

public class CheckPointRequest extends ConditionalRequest {

	////////// REQUEST ////////////

	public CheckPointRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request request = new CheckPointRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "CHECKPOINT";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	@Override
	public void doClassicAction(PiranhaObject object, String target) {
		String data = target.toUpperCase();
		CheckPointSet.getSet().add(data);
		System.out.println("CHECKPOINT : " + data);
	}

	@Override
	protected String getConditionToCheck(PiranhaObject object) {
		return null;
	}

	@Override
	protected boolean analyseCondition(PiranhaObject object, String target) {
		String valueToCheck = String.valueOf(target.split("\\?")[0]).toUpperCase();
		return CheckPointSet.getSet().contains(valueToCheck);
	}

}
