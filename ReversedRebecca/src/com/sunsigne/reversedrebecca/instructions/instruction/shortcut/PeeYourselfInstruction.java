package com.sunsigne.reversedrebecca.instructions.instruction.shortcut;

import com.sunsigne.reversedrebecca.instructions.InstructionList;
import com.sunsigne.reversedrebecca.instructions.instruction.Instruction;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.LivingObject;
import com.sunsigne.reversedrebecca.object.other.PeePuddle;

public class PeeYourselfInstruction implements Instruction {

	////////// INSTRUCTION ////////////

	public PeeYourselfInstruction() {
		InstructionList.getList().addObject(this);
	}

	private static Instruction instruction = new PeeYourselfInstruction();

	@Override
	public Instruction getInstruction() {
		return instruction;
	}

	@Override
	public String getType() {
		return "PEEING";
	}
	
	@Override
	public boolean isShortcut() {
		return true;
	}
	
	@Override
	public void doAction(LivingObject living, String target) {
		int x = living.getX();
		int y = living.getY();

		living.getHandler().getList().add(0, new PeePuddle(x, y));
	}

}
