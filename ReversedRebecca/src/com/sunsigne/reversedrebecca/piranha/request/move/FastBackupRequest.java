package com.sunsigne.reversedrebecca.piranha.request.move;

import com.sunsigne.reversedrebecca.object.characteristics.SpeedVariator.SPEEDNESS;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class FastBackupRequest extends MoveBackupRequest {

	////////// REQUEST ////////////

	public FastBackupRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request request = new FastBackupRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "FAST_BACKUP";
	}

	@Override
	public SPEEDNESS getSpeedness() {
		return SPEEDNESS.FAST;
	}

}
