package com.sunsigne.reversedrebecca.instructions.instruction.shortcut;

import com.sunsigne.reversedrebecca.instructions.InstructionList;
import com.sunsigne.reversedrebecca.instructions.instruction.Instruction;

public class MoveSlowlyInstruction extends MoveInstruction {

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
		return "SLOW";
	}

	@Override
	public boolean isRunning() {
		return false;
	}

}
