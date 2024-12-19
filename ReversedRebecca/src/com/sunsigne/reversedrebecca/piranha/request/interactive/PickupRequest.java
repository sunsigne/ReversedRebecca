package com.sunsigne.reversedrebecca.piranha.request.interactive;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.loot.LootObject;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.IndexRequest;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.system.Size;

public class PickupRequest implements IndexRequest {

	////////// REQUEST ////////////

	public PickupRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request action = new PickupRequest();

	@Override
	public Request getRequest() {
		return action;
	}

	@Override
	public String getType() {
		return "PICKUP";
	}

	@Override
	public boolean hasCompactWriting() {
		return false;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {

		GameObject gameOject = getGameObject(object, target, Size.XS / 2);
		if (gameOject instanceof LootObject == false)
			return;

		LootObject lootable = (LootObject) gameOject;
		lootable.pickup();
	}

}
