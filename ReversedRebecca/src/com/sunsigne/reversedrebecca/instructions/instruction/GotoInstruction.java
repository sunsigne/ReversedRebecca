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
	public boolean isShortcut() {
		return false;
	}

	@Override
	public void doAction(LivingObject living, String target) {
		if (living instanceof NPC == false)
			return;

		NPC npc = (NPC) living;

		String[] values = target.split(",");
		String statement = "$->";

		for (String tempValue : values) {

			for (Instruction tempInstruction : InstructionList.getList().getList()) {
				if (!tempInstruction.isShortcut())
					continue;

				if (tempValue.contains(tempInstruction.getType()))
					tempInstruction.doAction(living, tempValue.split(":")[1]);
			}

			if (!tempValue.equalsIgnoreCase("null"))
				new InstructionAnalyzer(npc, statement + tempValue);
		}
	}

}
