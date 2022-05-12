package com.sunsigne.reversedrebecca.piranha.request.state;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.object.piranha.living.characteristics.PlayerAvoider;
import com.sunsigne.reversedrebecca.object.piranha.living.characteristics.PlayerAvoider.AVOIDERTYPE;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.piranha.request.conditional.ConditionalRequest;

public class PlayerAvoiderRequest extends ConditionalRequest {

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
	public void doClassicAction(PiranhaObject object, String target) {
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

	@Override
	protected String getConditionToCheck(PiranhaObject object) {
		if (object instanceof PlayerAvoider == false)
			return "false";

		PlayerAvoider avoiderObject = (PlayerAvoider) object;
		return avoiderObject.getPlayerAvoiderType().getName();
	}

}
