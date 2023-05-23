package com.sunsigne.reversedrebecca.piranha.condition.global;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.piranha.condition.GlobalInstruction;
import com.sunsigne.reversedrebecca.piranha.condition.GlobalInstructionList;

public class TimeCondition extends GlobalInstruction {
	
	////////// GLOBAL INSTRUCTION ////////////

	public TimeCondition() {
		GlobalInstructionList.getList().addObject(this);
	}
	
	private static GlobalInstruction globalInstruction = new TimeCondition();

	@Override
	public GlobalInstruction getGlobalInstruction() {
		return globalInstruction;
	}

	@Override
	public String getConditionType() {
		return "TIME->";
	}
	
	public void registerValue(int time) {
		String value = String.valueOf(time);
		analyse(getConditionType() + value);
	}

	////////// OPTIMIZATION ////////////

	private static GameList<PiranhaObject> exceptions = new GameList<>(LISTTYPE.ARRAY);

	@Override
	public GameList<PiranhaObject> getExceptionsList() {
		return exceptions;
	}
	
}
