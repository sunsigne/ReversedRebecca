package com.sunsigne.reversedrebecca.instructions.instruction;

import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.npc.NPC;

public interface Instruction {

	////////// INSTRUCTION ////////////

	Instruction getInstruction();

	String getType();

	void doAction(NPC npc, String target);

}
