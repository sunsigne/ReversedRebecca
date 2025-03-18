package com.sunsigne.reversedrebecca.piranha.request.ressources;

import com.sunsigne.reversedrebecca.characteristics.tools.InventoryPlayer;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class HUDRemoveRequest implements Request {

	////////// REQUEST ////////////

	public HUDRemoveRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request action = new HUDRemoveRequest();

	@Override
	public Request getRequest() {
		return action;
	}

	@Override
	public String getType() {
		return "HUD_REMOVE";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {
		int index = Integer.parseInt(target);
		new InventoryPlayer().removeItem(index - 1);
	}

}
