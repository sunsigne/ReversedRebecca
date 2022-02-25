package com.sunsigne.reversedrebecca.instructions.instruction;

import com.sunsigne.reversedrebecca.instructions.InstructionList;
import com.sunsigne.reversedrebecca.instructions.Statement;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.npc.NPC;

public class GotoInstruction implements Instruction {

	////////// INSTRUCTION ////////////

	public GotoInstruction() {
		InstructionList.getList().addObject(this);
	}

	private static Instruction instruction = new GotoInstruction();

	@Override
	public Instruction getInstruction() {
		return instruction;
	}

	@Override
	public String getType() {
		return "GOTO";
	}

	@Override
	public void doAction(NPC npc, String target) {
		String[] values = target.split(",");

		for (String tempValue : values) {
			new Statement().got0(npc, tempValue);
		}
	}

}
