package com.sunsigne.reversedrebecca.piranha.request.move;

import com.sunsigne.reversedrebecca.object.characteristics.SpeedVariator.SPEEDNESS;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class SnailRequest extends MoveRequest {

	////////// REQUEST ////////////

	public SnailRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request request = new SnailRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "SNAIL";
	}

	@Override
	public SPEEDNESS getSpeedness() {
		return SPEEDNESS.SNAIL;
	}

}
