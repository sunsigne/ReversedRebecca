package com.sunsigne.reversedrebecca.piranha.condition.local;

import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.piranha.condition.LocalInstruction;

public class GotoCondition extends LocalInstruction {

	public void registerValue(ExtraBehaviorsObject object, String value) {
		String conditionType = "$->";

		analyse(object, conditionType + value);
	}

}
