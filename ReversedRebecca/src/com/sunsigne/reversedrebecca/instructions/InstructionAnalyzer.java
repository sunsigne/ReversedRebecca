package com.sunsigne.reversedrebecca.instructions;

import com.sunsigne.reversedrebecca.instructions.instruction.Instruction;
import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.ressources.FileTask;

public class InstructionAnalyzer {

	public InstructionAnalyzer(ExtraBehaviorsObject object, String value) {
		this.object = object;

		// if NPC has no InstructionMap
		if (new FileTask().doesExist(object.getInstructionMap()) == false)
			return;

		instruction = new FileTask().read(value.toUpperCase(), object.getInstructionMap());

		// if Statement has no correlated Instruction
		if (instruction.isBlank())
			return;

		processInstruction();
	}

	private ExtraBehaviorsObject object;
	private String instruction;

	private void processInstruction() {
		String instructionType = instruction.split("->")[0];
		String target = instruction.split("->")[1];

		for (Instruction tempInstruction : InstructionList.getList().getList()) {
			if (instructionType.equalsIgnoreCase(tempInstruction.getType())) {
				tempInstruction.doAction(object, target);
			}
		}
	}

}
