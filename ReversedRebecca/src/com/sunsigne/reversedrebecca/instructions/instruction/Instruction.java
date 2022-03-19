package com.sunsigne.reversedrebecca.instructions.instruction;

import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.LivingObject;

public interface Instruction {

	////////// INSTRUCTION ////////////

	Instruction getInstruction();

	String getType();
	
	boolean isShortcut();
	
	void doAction(LivingObject living, String target);
	


}
