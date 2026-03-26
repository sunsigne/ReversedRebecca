package com.sunsigne.reversedrebecca.piranha.request.move;

import com.sunsigne.reversedrebecca.object.characteristics.Pusher;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class PushRequest extends PushTimerRequest {

	////////// REQUEST ////////////

	public PushRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request request = new PushRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "PUSH";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {
		super.doAction(object, target + "," + Pusher.PUSHING_TIME);
	}

}
