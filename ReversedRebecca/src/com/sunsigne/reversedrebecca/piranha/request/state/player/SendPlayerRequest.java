package com.sunsigne.reversedrebecca.piranha.request.state.player;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.pattern.player.PlayerLayerChanger;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class SendPlayerRequest implements Request {

	////////// REQUEST ////////////

	public SendPlayerRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request request = new SendPlayerRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "SEND_PLAYER";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {
		if (target.equalsIgnoreCase("up"))
			new PlayerLayerChanger().goesUp();
		else if (target.equalsIgnoreCase("down"))
			new PlayerLayerChanger().goesDown();
	}

}
