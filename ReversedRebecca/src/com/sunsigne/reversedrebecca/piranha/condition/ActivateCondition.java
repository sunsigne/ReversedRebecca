package com.sunsigne.reversedrebecca.piranha.condition;

import com.sunsigne.reversedrebecca.piranha.MassiveInstruction;

public class ActivateCondition extends MassiveInstruction {

	public void registerValue(String value) {
		String conditionType = "!->";

		analyse(conditionType + value);
	}

}
