package com.sunsigne.reversedrebecca.object.extrabehaviors.livings.npc;

import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.ressources.FileTask;

public class Instruction {

	public Instruction(NPC npc, String valueToRead) {
		this.npc = npc;
		if (new FileTask().doesExist(npc.getInstructionMap()) == false)
			return;

		instruction = new FileTask().read(valueToRead.toUpperCase(), npc.getInstructionMap());
		readInstruction();
	}

	////////// ACTION ////////////

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

	// facing x 2 / goal est une fin en soit / lost puzzle
	private void readInstruction() {

		String instructionType = getInstructionType();
		String target = instruction.replace(instructionType + "->", "");

		switch (instructionType) {
		case "NAME":
			npc.setName(target);
			break;

		case "FACING":
			setFacing(target);
			break;

		case "GOTO":
			movingInstruction(target, false);
			break;

		case "TP":
		case "TELEPORT":
			movingInstruction(target, true);
			break;

		case "INSTRUCTION":
			processInstruction(target);
			break;
		}
	}

	private void setFacing(String target) {
		for (DIRECTION tempFacing : DIRECTION.values()) {
			if (tempFacing.getName().equalsIgnoreCase(target))
				npc.setFacing(tempFacing);
		}
	}

	private void movingInstruction(String target, boolean teleport) {
		int x = Integer.parseInt(target.split(",")[0]);
		int y = Integer.parseInt(target.split(",")[1]);

		GoalObject goal = new GoalObject(x, y, false);

		if (teleport) {
			npc.setX(goal.getX());
			npc.setY(goal.getY());
		} else
			npc.setGoal(goal);
	}

	private void processInstruction(String target) {

		String[] split_instructions = target.split(",");

		for (String tempInstruction : split_instructions) {
			String instruction = "INSTRUCTION->" + tempInstruction;
			new Instruction(npc, instruction);
		}
	}

}
