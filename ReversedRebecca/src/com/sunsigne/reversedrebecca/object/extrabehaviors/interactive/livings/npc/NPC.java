package com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.npc;

import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.Behavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.behaviors.BlockingPath;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.LivingObject;

public class NPC extends LivingObject {

	public NPC(String name, int x, int y) {
		super(name, x, y, AVOIDERTYPE.AROUND);
		addNPCBehaviors();
	}

	////////// BEHAVIOR ////////////

	public Behavior blockingPath;

	private void addNPCBehaviors() {

		blockingPath = new BlockingPath(this);
		addBehavior(blockingPath);
	}

}
