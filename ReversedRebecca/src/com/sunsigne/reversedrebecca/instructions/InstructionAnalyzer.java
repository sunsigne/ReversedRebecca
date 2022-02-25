package com.sunsigne.reversedrebecca.instructions;

import com.sunsigne.reversedrebecca.instructions.instruction.Instruction;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.npc.NPC;
import com.sunsigne.reversedrebecca.ressources.FileTask;

public class InstructionAnalyzer {

	public InstructionAnalyzer(NPC npc, String value) {
		this.npc = npc;

		// if NPC has no InstructionMap
		if (new FileTask().doesExist(npc.getInstructionMap()) == false)
			return;

		instruction = new FileTask().read(value.toUpperCase(), npc.getInstructionMap());

		// if Statement has no correlated Instruction
		if (instruction.isBlank())
			return;

		processInstruction();
	}

	private NPC npc;
	private String instruction;

	private void processInstruction() {
		String instructionType = instruction.split("->")[0];
		String target = instruction.split("->")[1];

		for (Instruction tempInstruction : InstructionList.getList().getList()) {
			if (instructionType.equalsIgnoreCase(tempInstruction.getType())) {
				tempInstruction.doAction(npc, target);
			}
		}
	}

}
