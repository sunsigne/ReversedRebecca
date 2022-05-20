package com.sunsigne.reversedrebecca.piranha.condition.global;

import com.sunsigne.reversedrebecca.piranha.condition.GlobalInstruction;

public class SavedCondition extends GlobalInstruction {

	public void registerValue(String value) {
		String conditionType = "SAVED->";

		analyse(conditionType + value);
	}
	
}
