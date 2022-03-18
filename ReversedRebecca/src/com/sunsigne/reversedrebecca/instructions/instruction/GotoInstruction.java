package com.sunsigne.reversedrebecca.instructions.instruction;

import com.sunsigne.reversedrebecca.instructions.InstructionAnalyzer;
import com.sunsigne.reversedrebecca.instructions.InstructionList;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.LivingObject;
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
	public void doAction(LivingObject living, String target) {
		if (living instanceof NPC == false)
			return;

		NPC npc = (NPC) living;
		
		String[] values = target.split(",");
		String statement = "$->";

		for (String tempValue : values) {
			new InstructionAnalyzer(npc, statement + tempValue);
		}
	}

}
