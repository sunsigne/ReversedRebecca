package com.sunsigne.reversedrebecca.piranha.request.compact;

import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class SlowRequest extends MoveRequest {

	////////// REQUEST ////////////

	public SlowRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request request = new SlowRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "SLOW";
	}

	@Override
	public boolean isRunning() {
		return false;
	}

}
