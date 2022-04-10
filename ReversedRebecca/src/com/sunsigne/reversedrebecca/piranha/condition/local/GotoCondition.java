package com.sunsigne.reversedrebecca.piranha.condition.local;

import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.piranha.InstructionAnalyzer;

public class GotoCondition extends InstructionAnalyzer {

	public void registerValue(ExtraBehaviorsObject object, String value) {
		String conditionType = "$->";

		analyse(object, conditionType + value);
	}

}
