package com.sunsigne.reversedrebecca.piranha.request.ressources;

import com.sunsigne.reversedrebecca.characteristics.tools.InventoryPlayer;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class HUDAddRequest implements Request {

	////////// REQUEST ////////////

	public HUDAddRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request action = new HUDAddRequest();

	@Override
	public Request getRequest() {
		return action;
	}

	@Override
	public String getType() {
		return "HUD_ADD";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {
		int col = Integer.parseInt(target);
		new InventoryPlayer().addItem(col);
	}

}
