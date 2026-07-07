package com.sunsigne.reversedrebecca.piranha.request.state.player;

import com.sunsigne.reversedrebecca.characteristics.drunk.DrunkTask;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class ResetDrunkPlayer implements Request {

	////////// REQUEST ////////////

	public ResetDrunkPlayer() {
		new RequestList().addRequest(this, getType());
	}

	private static Request request = new ResetDrunkPlayer();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "RESET_DRUNK_PLAYER";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {
		new DrunkTask().setDrunk(0);
	}

}
