package com.sunsigne.reversedrebecca.piranha.request.ressources;

import com.sunsigne.reversedrebecca.characteristics.tools.InventoryPlayer;
import com.sunsigne.reversedrebecca.object.hud.HUD;
import com.sunsigne.reversedrebecca.object.hud.HUDList;
import com.sunsigne.reversedrebecca.object.hud.nurse.HUDNurseHealth;
import com.sunsigne.reversedrebecca.object.hud.nurse.HUDNurseTool;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.pattern.listener.ConditionalListener;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
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
		GenericListener generic = () -> new InventoryPlayer().addItem(col);

		if (LAYER.LOADING.getHandler().getList().isEmpty())
			generic.doAction();
		else
			waitforHUD(object, generic);
	}

	private void createNurseHud() {
		for (HUD tempHUD : HUDList.getList().getList())
			tempHUD.setVisible(false);

		HUD tools = new HUDNurseTool();
		LAYER.HUD.getHandler().getList().add(0, tools);
		Handler.updateHandlerMap(LAYER.HUD.getHandler(), tools);

		HUD health = new HUDNurseHealth();
		LAYER.HUD.getHandler().getList().add(0, health);
		Handler.updateHandlerMap(LAYER.HUD.getHandler(), health);
	}

	private void waitforHUD(PiranhaObject object, GenericListener generic) {

		ConditionalListener listener = new ConditionalListener() {

			@Override
			public boolean canDoAction() {
				return LAYER.LOADING.getHandler().getList().isEmpty();
			}

			@Override
			public GenericListener getAction() {
				return generic;
			}
		};

		object.setWaitfor(listener);
	}

}
