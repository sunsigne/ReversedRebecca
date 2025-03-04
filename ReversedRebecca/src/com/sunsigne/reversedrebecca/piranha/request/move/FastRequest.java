package com.sunsigne.reversedrebecca.piranha.request.move;

import com.sunsigne.reversedrebecca.object.characteristics.SpeedVariator.SPEEDNESS;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class FastRequest extends MoveRequest {

	////////// REQUEST ////////////

	public FastRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request request = new FastRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "FAST";
	}

	@Override
	public SPEEDNESS getSpeedness() {
		return SPEEDNESS.FAST;
	}

}
