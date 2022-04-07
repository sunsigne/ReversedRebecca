package com.sunsigne.reversedrebecca.instructions.instruction;

import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;

public interface Instruction {

	////////// INSTRUCTION ////////////

	Instruction getInstruction();

	String getType();

	boolean isShortcut();

	void doAction(ExtraBehaviorsObject object, String target);

}
