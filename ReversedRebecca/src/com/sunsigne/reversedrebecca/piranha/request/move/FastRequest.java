package com.sunsigne.reversedrebecca.piranha.request.move;

import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.characteristics.SpeedVariator.SPEEDTYPE;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class FastRequest extends MoveRequest {

	////////// REQUEST ////////////

	public FastRequest() {
		RequestList.getList().addObject(this);
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
	public SPEEDTYPE getSpeedType() {
		return SPEEDTYPE.FAST;
	}


}
