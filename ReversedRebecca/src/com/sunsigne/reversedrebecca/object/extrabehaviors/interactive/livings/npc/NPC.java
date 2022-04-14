package com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.npc;

import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.Behavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.behaviors.BlockingPath;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.LivingObject;
import com.sunsigne.reversedrebecca.system.Size;

public class NPC extends LivingObject {

	public NPC(String name, int x, int y) {
		super(name, x, y, Size.XS / 10, Size.XS / 5, COLLISIONTYPE.AROUND);
		addNPCBehaviors();
	}

	////////// BEHAVIOR ////////////

	public Behavior blockingPath;

	private void addNPCBehaviors() {

		blockingPath = new BlockingPath(this);
		addBehavior(blockingPath);
	}

	@Override
	public Behavior[] behaviorToPauseIfStunned() {
		return new Behavior[] { movingToGoal };
	}

}
