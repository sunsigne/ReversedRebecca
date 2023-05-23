package com.sunsigne.reversedrebecca.piranha.condition.global;

import com.sunsigne.reversedrebecca.object.characteristics.Pushable;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.piranha.condition.GlobalInstruction;
import com.sunsigne.reversedrebecca.piranha.condition.GlobalInstructionList;

public class PushedCondition extends GlobalInstruction {
	
	////////// GLOBAL INSTRUCTION ////////////
	
	public PushedCondition() {
		GlobalInstructionList.getList().addObject(this);
	}
	
	private static GlobalInstruction globalInstruction = new PushedCondition();

	@Override
	public GlobalInstruction getGlobalInstruction() {
		return globalInstruction;
	}

	@Override
	public String getConditionType() {
		return "PUSHED->";
	}
	
	public void registerValue(Pushable pushable) {
		if (pushable instanceof PiranhaObject == false)
			return;

		String value = ((PiranhaObject) pushable).getName();
		analyse(getConditionType() + value);
	}
	
	////////// OPTIMIZATION ////////////

	private static GameList<PiranhaObject> exceptions = new GameList<>(LISTTYPE.ARRAY);

	@Override
	public GameList<PiranhaObject> getExceptionsList() {
		return exceptions;
	}
	
}
