package com.sunsigne.reversedrebecca.instructions.instruction.shortcut;

import com.sunsigne.reversedrebecca.instructions.InstructionList;
import com.sunsigne.reversedrebecca.instructions.instruction.Instruction;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.LivingObject;

public class NameInstruction implements Instruction {

	////////// INSTRUCTION ////////////

	public NameInstruction() {
		InstructionList.getList().addObject(this);
	}

	private static Instruction instruction = new NameInstruction();

	@Override
	public Instruction getInstruction() {
		return instruction;
	}

	@Override
	public String getType() {
		return "NAME";
	}
	
	@Override
	public boolean isShortcut() {
		return true;
	}

	@Override
	public void doAction(LivingObject living, String target) {
		living.setName(target);
	}


}
