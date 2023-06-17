package com.sunsigne.reversedrebecca.piranha.request.ressources;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class CounterThreeRequest extends CounterRequest {

	////////// REQUEST ////////////

	public CounterThreeRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request request = new CounterThreeRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "COUNTER_THREE";
	}

	@Override
	public void doAction(PiranhaObject object, String target) {
		super.doAction(object, "3," + target);
	}

}
