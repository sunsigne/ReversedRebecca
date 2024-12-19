package com.sunsigne.reversedrebecca.piranha.request.ressources;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class CounterTwoRequest extends CounterRequest {

	////////// REQUEST ////////////

	public CounterTwoRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request request = new CounterTwoRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "COUNTER_TWO";
	}

	@Override
	public void doAction(PiranhaObject object, String target) {
		super.doAction(object, "2," + target);
	}

}
