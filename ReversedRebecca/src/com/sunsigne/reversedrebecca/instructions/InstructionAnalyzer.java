package com.sunsigne.reversedrebecca.instructions;

import com.sunsigne.reversedrebecca.instructions.instruction.Instruction;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.npc.NPC;
import com.sunsigne.reversedrebecca.ressources.FileTask;

public class InstructionAnalyzer {

	protected InstructionAnalyzer(NPC npc, String value) {
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

	private String getInstructionType() {

		String instructionType = "";

		int size = instruction.length();
		if (size == 0)
			return null;

		for (int index = 0; index < size; index++) {
			char c = instruction.charAt(index);
			if (c == '-')
				return instructionType;
			instructionType = instructionType.concat(Character.toString(c));
		}

		System.err.println("Syntax Error in file " + npc.getInstructionMap());
		System.err.println("An instruction should always have a -> target");
		return instructionType;
	}

	private void processInstruction() {
		String instructionType = getInstructionType();
		String actionData = instruction.replace(instructionType + "->", "");

		for (Instruction tempInstruction : InstructionList.getList().getList()) {
			if (instructionType.equalsIgnoreCase(tempInstruction.getType())) {
				tempInstruction.doAction(npc, actionData);
			}
		}
	}

}
