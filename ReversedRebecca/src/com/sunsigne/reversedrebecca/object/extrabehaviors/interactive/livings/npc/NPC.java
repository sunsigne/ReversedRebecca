package com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.npc;

import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.LivingObject;

public class NPC extends LivingObject {

	public NPC(String name, int x, int y) {
		super(name, x, y, AVOIDERTYPE.AROUND);
	}

	////////// BEHAVIOR ////////////

}
