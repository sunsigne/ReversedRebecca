package com.sunsigne.reversedrebecca.piranha.condition.global;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.piranha.condition.GlobalInstruction;
import com.sunsigne.reversedrebecca.piranha.condition.GlobalInstructionList;

public class TalkedCondition extends GlobalInstruction {

	////////// GLOBAL INSTRUCTION ////////////

	public TalkedCondition() {
		GlobalInstructionList.getList().addObject(this);
	}
	
	private static GlobalInstruction globalInstruction = new TalkedCondition();

	@Override
	public GlobalInstruction getGlobalInstruction() {
		return globalInstruction;
	}

	@Override
	public String getConditionType() {
		return "TALKED->";
	}

	public void registerValue(String value) {
		analyse(getConditionType() + value);
	}

	////////// OPTIMIZATION ////////////

	private static GameList<PiranhaObject> exceptions = new GameList<>(LISTTYPE.ARRAY);

	@Override
	public GameList<PiranhaObject> getExceptionsList() {
		return exceptions;
	}

}
