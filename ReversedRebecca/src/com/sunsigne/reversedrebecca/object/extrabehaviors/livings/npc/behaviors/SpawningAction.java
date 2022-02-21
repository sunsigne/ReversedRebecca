package com.sunsigne.reversedrebecca.object.extrabehaviors.livings.npc.behaviors;

import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.TickBehavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.npc.NPC;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.npc.NPCAction;

public class SpawningAction implements TickBehavior {

	public SpawningAction(NPC npc) {
		this.npc = npc;
	}

	////////// BEHAVIOR ////////////

	private NPC npc;

	@Override
	public NPC getExtraBehaviorsObject() {
		return npc;
	}

	////////// TICK ////////////

	@Override
	public void tick() {

		if (npc.getActionMap() == null)
			return;

		new NPCAction(getExtraBehaviorsObject(), "SPAWNING");
		npc.removeBehavior(this);
	}

}
