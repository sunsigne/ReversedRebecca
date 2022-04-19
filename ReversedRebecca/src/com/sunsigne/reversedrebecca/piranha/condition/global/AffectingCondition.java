package com.sunsigne.reversedrebecca.piranha.condition.global;

import com.sunsigne.reversedrebecca.piranha.condition.GlobalInstruction;

public class AffectingCondition extends GlobalInstruction {

	public void registerValue(String value) {
		String conditionType = "!->";

		analyse(conditionType + value);
	}

}
