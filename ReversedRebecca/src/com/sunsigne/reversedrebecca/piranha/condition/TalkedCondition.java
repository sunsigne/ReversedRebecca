package com.sunsigne.reversedrebecca.piranha.condition;

import com.sunsigne.reversedrebecca.piranha.MassiveInstruction;

public class TalkedCondition extends MassiveInstruction {

	public void registerValue(String value) {
		String conditionType = "TALKED->";

		analyse(conditionType + value);
	}

}
