package com.sunsigne.reversedrebecca.piranha.condition;

import com.sunsigne.reversedrebecca.piranha.MassiveInstruction;

public class TimeCondition extends MassiveInstruction {

	public void registerValue(int time) {
		String conditionType = "TIME->";

		String value = String.valueOf(time);

		analyse(conditionType + value);
	}
	
}
