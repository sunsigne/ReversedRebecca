package com.sunsigne.reversedrebecca.piranha.request.compact;

import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.piranha.ConditionalAnalyser;
import com.sunsigne.reversedrebecca.piranha.MemoryList;
import com.sunsigne.reversedrebecca.piranha.RequestList;
import com.sunsigne.reversedrebecca.piranha.request.GotoRequest;
import com.sunsigne.reversedrebecca.piranha.request.Request;

public class MemoryRequest implements Request {

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
		ConditionalAnalyser conditional = ConditionalAnalyser.create(target);

		if (conditional != null)
			checkTag(conditional, object, target);
		else
			registerMemory(object, target);
	}


	private void checkTag(ConditionalAnalyser conditional, ExtraBehaviorsObject object, String target) {
		for (String tempMemory : MemoryList.getList().getList()) {
			if (tempMemory.equalsIgnoreCase(conditional.getValueToCheck()))
				conditional.setMet(true);
		}

		Request request = RequestList.getList().getObject(new GotoRequest());
		request.doAction(object, conditional.getAction());
	}


	private void registerMemory(ExtraBehaviorsObject object, String target) {
		MemoryList.getList().addObject(target);
	}
	
}
