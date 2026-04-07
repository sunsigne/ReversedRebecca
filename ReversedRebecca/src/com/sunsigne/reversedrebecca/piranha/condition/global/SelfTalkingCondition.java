package com.sunsigne.reversedrebecca.piranha.condition.global;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.piranha.condition.GlobalInstruction;
import com.sunsigne.reversedrebecca.piranha.condition.GlobalInstructionList;

public class SelfTalkingCondition extends GlobalInstruction {

	////////// GLOBAL INSTRUCTION ////////////

	public SelfTalkingCondition() {
		GlobalInstructionList.getList().addObject(this);
	}

	private static GlobalInstruction globalInstruction = new SelfTalkingCondition();

	@Override
	public GlobalInstruction getGlobalInstruction() {
		return globalInstruction;
	}

	@Override
	public String getConditionType() {
		return "SELFTALKING->";
	}

	public void registerValue(PiranhaObject object, String value) {
		this.piranhaFile = object.getPiranhaFile();
		analyse(getConditionType() + value);
	}

	private String piranhaFile;

	@Override
	protected boolean isAnalysePrevented(String condition, PiranhaObject object) {
		return object.getPiranhaFile().equalsIgnoreCase(piranhaFile) == false;
	}

	////////// OPTIMIZATION ////////////

	private static GameList<PiranhaObject> exceptions = new GameList<>(LISTTYPE.ARRAY);

	@Override
	public GameList<PiranhaObject> getExceptionsList() {
		return exceptions;
	}

}
