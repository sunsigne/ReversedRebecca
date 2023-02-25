package com.sunsigne.reversedrebecca.piranha.request.state;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.piranha.request.conditional.ConditionalRequest;

public class WaitforStunRequest extends ConditionalRequest {

	////////// REQUEST ////////////

	public WaitforStunRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request request = new WaitforStunRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "WAITFOR_STUN";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	@Override
	public void doClassicAction(PiranhaObject object, String target) {
		boolean stun_at_waitfor_attribution = Boolean.parseBoolean(target);
		object.set_stun_at_waitfor_attribution(stun_at_waitfor_attribution);
	}

	@Override
	protected String getConditionToCheck(PiranhaObject object) {
		return String.valueOf(object.does_stun_at_waitfor_attribution());
	}

}
