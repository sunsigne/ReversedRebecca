package com.sunsigne.reversedrebecca.piranha.condition.global;

import com.sunsigne.reversedrebecca.piranha.condition.GlobalInstruction;

public class TimeCondition extends GlobalInstruction {

	public void registerValue(int time) {
		String conditionType = "TIME->";

		String value = String.valueOf(time);

		analyse(conditionType + value);
	}
	
}
