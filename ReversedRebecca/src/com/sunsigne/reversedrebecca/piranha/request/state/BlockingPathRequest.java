package com.sunsigne.reversedrebecca.piranha.request.state;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.piranha.request.conditional.ConditionalRequest;

public class BlockingPathRequest extends ConditionalRequest {

	////////// REQUEST ////////////

	public BlockingPathRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request action = new BlockingPathRequest();

	@Override
	public Request getRequest() {
		return action;
	}

	@Override
	public String getType() {
		return "BLOCKING_PATH";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	@Override
	public void doClassicAction(PiranhaObject object, String target) {
		boolean blockingPath = Boolean.parseBoolean(target);
		object.setBlockingPath(blockingPath);
	}

	@Override
	protected String getConditionToCheck(PiranhaObject object) {
		return String.valueOf(object.isBlockingPath());
	}

}
