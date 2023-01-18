package com.sunsigne.reversedrebecca.piranha.condition.global;

import com.sunsigne.reversedrebecca.object.characteristics.Pushable;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.condition.GlobalInstruction;

public class PushedCondition extends GlobalInstruction {

	public void registerValue(Pushable pushable) {
		if (pushable instanceof PiranhaObject == false)
			return;

		String conditionType = "PUSHED->";

		String value = ((PiranhaObject) pushable).getName();

		analyse(conditionType + value);
	}

}
