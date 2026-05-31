package com.sunsigne.reversedrebecca.piranha.request.memory;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.piranha.request.conditional.ConditionalRequest;
import com.sunsigne.reversedrebecca.system.PausePreventer;
import com.sunsigne.reversedrebecca.system.PausePreventer.PAUSE_STATE;

public class MemoryRequest extends ConditionalRequest {

	////////// REQUEST ////////////

	public MemoryRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request request = new MemoryRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "MEMORY";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	@Override
	public void doClassicAction(PiranhaObject object, String target) {
		if (target.toUpperCase().contains("PAUSE_PREVENTION_PUSHUP")) {
			specialPausePreventionCase(target);
			return;
		}

		MemorySet.getSet().add(target.toUpperCase());
	}

	private void specialPausePreventionCase(String target) {
		boolean activate = target.toUpperCase().contains("ENABLE");
		PausePreventer.state = activate ? PAUSE_STATE.PUSHUPS : null;
	}

	@Override
	protected String getConditionToCheck(PiranhaObject object) {
		return null;
	}

	@Override
	protected boolean analyseCondition(PiranhaObject object, String target) {
		String valueToCheck = String.valueOf(target.split("\\?")[0]).toUpperCase();
		return MemorySet.getSet().contains(valueToCheck);
	}

}
