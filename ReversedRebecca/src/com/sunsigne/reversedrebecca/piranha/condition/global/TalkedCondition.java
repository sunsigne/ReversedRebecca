package com.sunsigne.reversedrebecca.piranha.condition.global;

import com.sunsigne.reversedrebecca.piranha.condition.GlobalInstruction;

public class TalkedCondition extends GlobalInstruction {

	public void registerValue(String value) {
		String conditionType = "TALKED->";

		analyse(conditionType + value);
	}

}
