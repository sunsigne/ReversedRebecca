package com.sunsigne.reversedrebecca.instructions.instruction;

import com.sunsigne.reversedrebecca.instructions.InstructionList;
import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;

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
		return "TP";
	}

	@Override
	public boolean isShortcut() {
		return false;
	}

	@Override
	public void doAction(ExtraBehaviorsObject object, String target) {
		int x = Integer.parseInt(target.split(",")[0]);
		int y = Integer.parseInt(target.split(",")[1]);

		GoalObject goal = new GoalObject(x, y, false);

		object.setX(goal.getX());
		object.setY(goal.getY());
	}

}
