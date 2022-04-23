package com.sunsigne.reversedrebecca.piranha.request.other;

import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.LivingObject;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.object.piranha.living.NPC;
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

//		PiranhaNPC npc = (PiranhaNPC) object;
//		AVOIDERTYPE avoider = AVOIDERTYPE.AROUND;

//		for (AVOIDERTYPE tempAvoider : AVOIDERTYPE.values()) {
//			if (tempAvoider.getName().equalsIgnoreCase(target) == false)
//				continue;

//			avoider = tempAvoider;
//		}
//		living.setPlayerAvoiderType(avoider);
	}

}
