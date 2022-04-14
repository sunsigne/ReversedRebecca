package com.sunsigne.reversedrebecca.piranha.request.compact;

import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.CollisionWithPlayer.COLLISIONTYPE;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.LivingObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.npc.NPC;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class CollisionWithPlayerRequest implements Request {

	////////// REQUEST ////////////

	public CollisionWithPlayerRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request action = new CollisionWithPlayerRequest();

	@Override
	public Request getRequest() {
		return action;
	}

	@Override
	public String getType() {
		return "COLLISION_WITH_PLAYER";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	@Override
	public void doAction(ExtraBehaviorsObject object, String target) {
		if (object instanceof LivingObject == false)
			return;

		NPC living = (NPC) object;

		for (COLLISIONTYPE tempCollision : COLLISIONTYPE.values()) {
			if (tempCollision.getName().equalsIgnoreCase(target) == false)
				continue;

			living.setCollisionType(tempCollision);
			return;
		}
		living.setCollisionType(COLLISIONTYPE.AROUND);
	}

}
