package com.sunsigne.reversedrebecca.instructions.instruction.shortcut;

import com.sunsigne.reversedrebecca.instructions.InstructionList;
import com.sunsigne.reversedrebecca.instructions.instruction.Instruction;
import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.object.other.PeePuddle;

public class PeeInstruction implements Instruction {

	////////// INSTRUCTION ////////////

	public PeeInstruction() {
		InstructionList.getList().addObject(this);
	}

	private static Instruction instruction = new PeeInstruction();

	@Override
	public Instruction getInstruction() {
		return instruction;
	}

	@Override
	public String getType() {
		return "PEE";
	}

	@Override
	public boolean isShortcut() {
		return true;
	}

	@Override
	public void doAction(ExtraBehaviorsObject object, String target) {
		int x = object.getX();
		int y = object.getY();
		object.getHandler().getList().add(0, new PeePuddle(x, y));
	}

}
