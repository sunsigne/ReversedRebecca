package com.sunsigne.reversedrebecca.piranha.request.conditional;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.object.piranha.living.LivingOption;
import com.sunsigne.reversedrebecca.object.piranha.living.LivingOption.LIVING_TYPE;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class PrefferedAttractionRequest extends ConditionalRequest {

	////////// REQUEST ////////////

	public PrefferedAttractionRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request request = new PrefferedAttractionRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "PREFFERED_ATTRACTION";
	}

	@Override
	public boolean hasCompactWriting() {
		return false;
	}

	@Override
	public void doClassicAction(PiranhaObject object, String target) {

	}

	@Override
	protected boolean analyseCondition(PiranhaObject object, String target) {
		return super.analyseCondition(object, target) || LivingOption.getType() == LIVING_TYPE.OPPOSITE;
	}

	@Override
	protected String getConditionToCheck(PiranhaObject object) {
		return LivingOption.getType().getName();
	}

}
