package com.sunsigne.reversedrebecca.instructions.instruction;

import com.sunsigne.reversedrebecca.instructions.InstructionList;
import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.npc.NPC;

public class MoveSlowlyInstruction implements Instruction {

	////////// INSTRUCTION ////////////

	public MoveSlowlyInstruction() {
		InstructionList.getList().addObject(this);
	}

	private static Instruction instruction = new MoveSlowlyInstruction();

	@Override
	public Instruction getInstruction() {
		return instruction;
	}

	@Override
	public String getType() {
		return "MSLOWLY";
	}

	@Override
	public void doAction(NPC npc, String target) {
		int x = Integer.parseInt(target.split(",")[0]);
		int y = Integer.parseInt(target.split(",")[1]);

		GoalObject goal = new GoalObject(x, y, false);

		npc.setRunning(false);
		npc.setGoal(goal);
	}

}
