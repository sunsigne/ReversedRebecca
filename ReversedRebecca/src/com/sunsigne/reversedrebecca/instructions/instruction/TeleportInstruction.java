package com.sunsigne.reversedrebecca.instructions.instruction;

import com.sunsigne.reversedrebecca.instructions.InstructionList;
import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.npc.NPC;

public class TeleportInstruction implements Instruction {

	////////// INSTRUCTION ////////////

	public TeleportInstruction() {
		InstructionList.getList().addObject(this);
	}

	private static Instruction instruction = new TeleportInstruction();

	@Override
	public Instruction getInstruction() {
		return instruction;
	}

	@Override
	public String getType() {
		return "TELEPORT";
	}

	@Override
	public void doAction(NPC npc, String target) {
		int x = Integer.parseInt(target.split(",")[0]);
		int y = Integer.parseInt(target.split(",")[1]);

		GoalObject goal = new GoalObject(x, y, false);

		npc.setX(goal.getX());
		npc.setY(goal.getY());
	}

}
