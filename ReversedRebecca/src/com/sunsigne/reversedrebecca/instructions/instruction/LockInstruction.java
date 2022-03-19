package com.sunsigne.reversedrebecca.instructions.instruction;

import com.sunsigne.reversedrebecca.instructions.InstructionList;
import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.LivingObject;
import com.sunsigne.reversedrebecca.object.puzzler.door.DoorObject;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;

public class LockInstruction implements Instruction {

	////////// INSTRUCTION ////////////

	public LockInstruction() {
		InstructionList.getList().addObject(this);
	}

	private static Instruction instruction = new LockInstruction();

	@Override
	public Instruction getInstruction() {
		return instruction;
	}

	@Override
	public String getType() {
		return "LOCK";
	}

	@Override
	public boolean isShortcut() {
		return false;
	}

	@Override
	public void doAction(LivingObject living, String target) {
		
		String pos = String.valueOf(target.split(":")[0]);
		int x = Integer.parseInt(pos.split("-")[0]);
		int y = Integer.parseInt(pos.split("-")[1]);
		LVL lvl = LVL.valueOf(target.split(":")[1]);

		GoalObject goal = new GoalObject(x, y, false);		
		Handler handler = living.getHandler();

		handler.removeObject(Handler.getObjectAtPos(handler, goal.getX(), goal.getY()));
		handler.addObject(new DoorObject(lvl, goal.getX(), goal.getY()));
	}

}
