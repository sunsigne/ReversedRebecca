package com.sunsigne.reversedrebecca.instructions.instruction.shortcut;

import com.sunsigne.reversedrebecca.instructions.InstructionList;
import com.sunsigne.reversedrebecca.instructions.instruction.Instruction;
import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;

public class DeleteInstruction implements Instruction {

	////////// INSTRUCTION ////////////

	public DeleteInstruction() {
		InstructionList.getList().addObject(this);
	}

	private static Instruction instruction = new DeleteInstruction();

	@Override
	public Instruction getInstruction() {
		return instruction;
	}

	@Override
	public String getType() {
		return "DELETE";
	}

	@Override
	public boolean isShortcut() {
		return true;
	}

	@Override
	public void doAction(ExtraBehaviorsObject object, String target) {

		// determinate the position

		String pos = String.valueOf(target);
		int x = Integer.parseInt(pos.split("-")[0]);
		int y = Integer.parseInt(pos.split("-")[1]);
		GoalObject goal = new GoalObject(x, y, false);

		// remove the object

		Handler handler = object.getHandler();
		handler.removeObject(Handler.getObjectAtPos(handler, goal.getX(), goal.getY()));
	}

}
