package com.sunsigne.reversedrebecca.piranha.request.other;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.object.piranha.living.characteristics.PlayerAvoider;
import com.sunsigne.reversedrebecca.object.piranha.living.characteristics.PlayerAvoider.AVOIDERTYPE;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class PlayerAvoiderRequest implements Request {

	////////// REQUEST ////////////

	public PlayerAvoiderRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request action = new PlayerAvoiderRequest();

	@Override
	public Request getRequest() {
		return action;
	}

	@Override
	public String getType() {
		return "PLAYER_AVOIDER_TYPE";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {
		if (object instanceof PlayerAvoider == false)
			return;

		PlayerAvoider avoiderObject = (PlayerAvoider) object;
		AVOIDERTYPE avoider = AVOIDERTYPE.AROUND;

		for (AVOIDERTYPE tempAvoider : AVOIDERTYPE.values()) {
			if (tempAvoider.getName().equalsIgnoreCase(target) == false)
				continue;

			avoider = tempAvoider;
		}
		avoiderObject.setPlayerAvoiderType(avoider);
	}

}
