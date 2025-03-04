package com.sunsigne.reversedrebecca.piranha.request.move;

import com.sunsigne.reversedrebecca.object.characteristics.SpeedVariator.SPEEDNESS;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class SlowRequest extends MoveRequest {

	////////// REQUEST ////////////

	public SlowRequest() {
		new RequestList().addRequest(this, getType());
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
	public SPEEDNESS getSpeedness() {
		return SPEEDNESS.SLOW;
	}

}
