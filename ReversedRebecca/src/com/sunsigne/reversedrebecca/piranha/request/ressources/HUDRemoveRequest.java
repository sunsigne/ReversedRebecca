package com.sunsigne.reversedrebecca.piranha.request.ressources;

import com.sunsigne.reversedrebecca.characteristics.tools.InventoryPlayer;
import com.sunsigne.reversedrebecca.object.hud.HUD;
import com.sunsigne.reversedrebecca.object.hud.HUDList;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.pattern.list.ListCloner;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.ressources.FileTask;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;

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

		String raw_col = new FileTask().read(false, target, "/textures/hud/inventory.txt");
		int col = Integer.parseInt(raw_col);
		new InventoryPlayer().removeItem(col);
	}

	private void removeNurseHud() {
		var clone = new ListCloner().deepCloneByClass(LAYER.HUD.getHandler(), HUD.class);

		for (HUD tempHUD : clone.getList()) {
			if (HUDList.getList().containsObject(tempHUD))
				tempHUD.setVisible(true);
			else
				tempHUD.removeObject();
		}
	}

}
