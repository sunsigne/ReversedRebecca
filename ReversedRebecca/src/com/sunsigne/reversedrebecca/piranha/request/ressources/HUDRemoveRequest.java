package com.sunsigne.reversedrebecca.piranha.request.ressources;

import com.sunsigne.reversedrebecca.characteristics.tools.InventoryPlayer;
import com.sunsigne.reversedrebecca.object.hud.HUD;
import com.sunsigne.reversedrebecca.object.hud.HUDList;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;

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
		if (target.equalsIgnoreCase("nurse")) {
			removeNurseHud();
			return;
		}

		int index = Integer.parseInt(target);
		new InventoryPlayer().removeItem(index - 1);
	}

	private void removeNurseHud() {
		LAYER.HUD.getHandler().clear();

		for (HUD tempHUD : HUDList.getList().getList()) {
			LAYER.HUD.getHandler().getList().add(0, tempHUD);
			Handler.updateHandlerMap(LAYER.HUD.getHandler(), tempHUD);
		}
	}

}
