package com.sunsigne.reversedrebecca.piranha.request.compact;

import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
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
	public void doAction(ExtraBehaviorsObject object, String target) {
		if (isConditional(target))
			doConditionalAction(object, target);
		else
			registerMemory(object, target);
	}

	@Override
	protected String getConditionToCheck(ExtraBehaviorsObject object) {
		return null;
	}
	
	@Override
	protected boolean analyseCondition(ExtraBehaviorsObject object, String target) {
		String valueToCheck = String.valueOf(target.split("\\?")[0]);

		for (String tempMemory : MemoryList.getList().getList()) {
			if (valueToCheck.equalsIgnoreCase(tempMemory))
				return true;
		}
		return false;
	}
	
	private void registerMemory(ExtraBehaviorsObject object, String target) {
		MemoryList.getList().addObject(target);
	}

}
