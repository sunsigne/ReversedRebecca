package com.sunsigne.reversedrebecca.piranha.request.move;

import com.sunsigne.reversedrebecca.object.characteristics.SpeedVariator.SPEEDNESS;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class SlowBackupRequest extends MoveBackupRequest {

	////////// REQUEST ////////////

	public SlowBackupRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request request = new SlowBackupRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "SLOW_BACKUP";
	}

	@Override
	public SPEEDNESS getSpeedness() {
		return SPEEDNESS.SLOW;
	}

}
