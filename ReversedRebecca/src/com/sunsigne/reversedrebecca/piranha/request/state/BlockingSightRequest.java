package com.sunsigne.reversedrebecca.piranha.request.state;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.piranha.request.conditional.ConditionalRequest;

public class BlockingSightRequest extends ConditionalRequest {

	////////// REQUEST ////////////

	public BlockingSightRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request action = new BlockingSightRequest();

	@Override
	public Request getRequest() {
		return action;
	}

	@Override
	public String getType() {
		return "BLOCKING_SIGHT";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	@Override
	public void doClassicAction(PiranhaObject object, String target) {
		boolean blockingSight = Boolean.parseBoolean(target);
		object.setBlockingSight(blockingSight);
	}

	@Override
	protected String getConditionToCheck(PiranhaObject object) {
		return String.valueOf(object.isBlockingSight());
	}

}
