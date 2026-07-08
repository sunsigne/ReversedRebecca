package com.sunsigne.reversedrebecca.piranha.request.move;

import com.sunsigne.reversedrebecca.object.characteristics.SpeedVariator.SPEEDNESS;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class SnailBackupRequest extends MoveBackupRequest {

	////////// REQUEST ////////////

	public SnailBackupRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request request = new SnailBackupRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "SNAIL_BACKUP";
	}

	@Override
	public SPEEDNESS getSpeedness() {
		return SPEEDNESS.SNAIL;
	}

}
