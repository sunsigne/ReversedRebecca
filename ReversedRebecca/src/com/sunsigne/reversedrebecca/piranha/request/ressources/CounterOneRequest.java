package com.sunsigne.reversedrebecca.piranha.request.ressources;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class CounterOneRequest extends CounterRequest {

	////////// REQUEST ////////////

	public CounterOneRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request request = new CounterOneRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "COUNTER_ONE";
	}

	@Override
	public void doAction(PiranhaObject object, String target) {
		super.doAction(object, "1," + target);
	}

}
