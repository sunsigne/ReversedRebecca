package com.sunsigne.reversedrebecca.piranha.request.memory;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.ConditionalRequest;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class MemoryRequest extends ConditionalRequest {

	////////// REQUEST ////////////

	public MemoryRequest() {
		RequestList.getList().addObject(this);
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
		MemoryList.getList().addObject(target);
	}

	@Override
	protected String getConditionToCheck(PiranhaObject object) {
		return null;
	}

	@Override
	protected boolean analyseCondition(PiranhaObject object, String target) {
		String valueToCheck = String.valueOf(target.split("\\?")[0]);

		for (String tempMemory : MemoryList.getList().getList()) {
			if (valueToCheck.equalsIgnoreCase(tempMemory))
				return true;
		}
		return false;
	}

}
