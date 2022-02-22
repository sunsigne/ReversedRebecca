package com.sunsigne.reversedrebecca.object.extrabehaviors.livings.npc;

import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.ressources.FileTask;

public class Instruction {

	public Instruction(NPC npc, String valueToRead) {
		this.npc = npc;
		if (npc.getName() == "error")
			return;

		action = new FileTask().read(valueToRead.toUpperCase(), npc.getInstructionMap());
		readInstruction();
	}

	////////// ACTION ////////////

	private NPC npc;
	private String action;

	private void readInstruction() {
		if (action.contains("GOTO")) {
			String pos = action.substring(6, action.length());
			gotoInstruction(pos);
			return;
		}

		if (action.contains("INSTRUCTION")) {
			String instructions = action.substring(13, action.length());
			processInstruction(instructions);
			return;
		}
	}

	private void gotoInstruction(String pos) {
		int x = Integer.parseInt(pos.split(",")[0]);
		int y = Integer.parseInt(pos.split(",")[1]);

		GoalObject goal = new GoalObject(x, y, false);
		npc.setGoal(goal);
	}

	private void processInstruction(String instructions) {

		String[] split_instructions = instructions.split(",");

		for (String tempInstruction : split_instructions) {
			String instruction = "INSTRUCTION->" + tempInstruction;
			new Instruction(npc, instruction);
		}
	}

}
