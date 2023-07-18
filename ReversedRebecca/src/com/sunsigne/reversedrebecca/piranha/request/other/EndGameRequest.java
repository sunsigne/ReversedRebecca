package com.sunsigne.reversedrebecca.piranha.request.other;

import com.sunsigne.reversedrebecca.menu.DemoEndScreen;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.world.World;

public class EndGameRequest implements Request {

	////////// REQUEST ////////////

	public EndGameRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request request = new EndGameRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "END_GAME";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {
		World.get().destroy();
		LAYER.MENU.addObject(new DemoEndScreen());
	}

}
