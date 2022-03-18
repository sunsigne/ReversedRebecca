package com.sunsigne.reversedrebecca.instructions.instruction;

import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.LivingObject;

public interface Instruction {

	////////// INSTRUCTION ////////////

	Instruction getInstruction();

	String getType();

	void doAction(LivingObject living, String target);

}
