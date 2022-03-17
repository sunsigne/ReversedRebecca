package com.sunsigne.reversedrebecca.instructions.instruction;

import com.sunsigne.reversedrebecca.instructions.InstructionList;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.npc.NPC;
import com.sunsigne.reversedrebecca.object.other.PeePuddle;

public class PeeYourselfInstruction implements Instruction {

	////////// INSTRUCTION ////////////

	public PeeYourselfInstruction() {
		InstructionList.getList().addObject(this);
	}

	private static Instruction instruction = new PeeYourselfInstruction();

	@Override
	public Instruction getInstruction() {
		return instruction;
	}

	@Override
	public String getType() {
		return "PEE";
	}

	@Override
	public void doAction(NPC npc, String target) {
		int x = npc.getX();
		int y = npc.getY();

		npc.getHandler().getList().add(0, new PeePuddle(x, y));
	}

}
