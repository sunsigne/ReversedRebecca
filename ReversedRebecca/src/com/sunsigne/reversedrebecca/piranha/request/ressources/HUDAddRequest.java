package com.sunsigne.reversedrebecca.piranha.request.ressources;

import com.sunsigne.reversedrebecca.characteristics.tools.InventoryPlayer;
import com.sunsigne.reversedrebecca.object.hud.HUD;
import com.sunsigne.reversedrebecca.object.hud.nurse.HUDNurseHealth;
import com.sunsigne.reversedrebecca.object.hud.nurse.HUDNurseTool;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;

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
		if (target.equalsIgnoreCase("nurse")) {
			createNurseHud();
			return;
		}

		int col = Integer.parseInt(target);
		new InventoryPlayer().addItem(col);
	}

	private void createNurseHud() {
		LAYER.HUD.getHandler().clear();

		HUD tools = new HUDNurseTool();
		LAYER.HUD.getHandler().getList().add(0, tools);
		Handler.updateHandlerMap(LAYER.HUD.getHandler(), tools);
		
		HUD health = new HUDNurseHealth();
		LAYER.HUD.getHandler().getList().add(0, health);
		Handler.updateHandlerMap(LAYER.HUD.getHandler(), health);
	}

}
