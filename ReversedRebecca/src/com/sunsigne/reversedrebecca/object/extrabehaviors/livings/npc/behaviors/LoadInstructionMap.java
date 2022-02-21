package com.sunsigne.reversedrebecca.object.extrabehaviors.livings.npc.behaviors;

import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.TickBehavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.npc.Instruction;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.npc.NPC;
import com.sunsigne.reversedrebecca.world.World;

public class LoadInstructionMap implements TickBehavior {

	public LoadInstructionMap(NPC npc) {
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
		if (World.get() == null)
			return;

		npc.setInstructionMap("maps/" + World.get().getMapName() + "/" + npc.getName() + ".csv");
		npc.removeBehavior(this);
	}

}
