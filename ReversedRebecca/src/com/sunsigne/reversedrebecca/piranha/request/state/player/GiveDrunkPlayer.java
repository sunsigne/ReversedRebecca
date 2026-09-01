package com.sunsigne.reversedrebecca.piranha.request.state.player;

import com.sunsigne.reversedrebecca.characteristics.drunk.DrunkTask;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class GiveDrunkPlayer implements Request {

	////////// REQUEST ////////////

	public GiveDrunkPlayer() {
		new RequestList().addRequest(this, getType());
	}

	private static Request request = new GiveDrunkPlayer();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "GIVE_DRUNK_PLAYER";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {
		int drunk = Integer.parseInt(target);

		if (drunk > 0)
			new DrunkTask().addDrunk(drunk);
		if (drunk < 0)
			new DrunkTask().removeDrunk(-drunk);
	}

}
