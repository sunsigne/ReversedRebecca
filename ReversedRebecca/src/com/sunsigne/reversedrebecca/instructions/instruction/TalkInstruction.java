package com.sunsigne.reversedrebecca.instructions.instruction;

import com.sunsigne.reversedrebecca.instructions.InstructionList;
import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.actions.action.ObjectAction;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.actions.action.TalkAction;

public class TalkInstruction implements Instruction {

	////////// INSTRUCTION ////////////

	public TalkInstruction() {
		InstructionList.getList().addObject(this);
	}

	private static Instruction instruction = new TalkInstruction();

	@Override
	public Instruction getInstruction() {
		return instruction;
	}

	@Override
	public String getType() {
		return "TALK";
	}

	@Override
	public boolean isShortcut() {
		return false;
	}

	@Override
	public void doAction(ExtraBehaviorsObject object, String target) {
		ObjectAction action = new TalkAction();
		action.setListener(action.getListener(object, target));
		action.doAction();
	}

}
