package com.sunsigne.reversedrebecca.instructions.instruction;

import com.sunsigne.reversedrebecca.instructions.InstructionList;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.npc.NPC;

public class NameInstruction implements Instruction {

	////////// INSTRUCTION ////////////

	public NameInstruction() {
		InstructionList.getList().addObject(this);
	}

	private static Instruction instruction = new NameInstruction();

	@Override
	public Instruction getInstruction() {
		return instruction;
	}

	@Override
	public String getType() {
		return "NAME";
	}

	@Override
	public void doAction(NPC npc, String target) {
		npc.setName(target);
	}

}
