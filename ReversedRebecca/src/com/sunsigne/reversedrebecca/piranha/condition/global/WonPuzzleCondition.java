package com.sunsigne.reversedrebecca.piranha.condition.global;

import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.piranha.condition.GlobalInstruction;
import com.sunsigne.reversedrebecca.piranha.condition.GlobalInstructionList;

public class WonPuzzleCondition extends GlobalInstruction {

	////////// GLOBAL INSTRUCTION ////////////

	public WonPuzzleCondition() {
		GlobalInstructionList.getList().addObject(this);
	}
	
	private static GlobalInstruction globalInstruction = new WonPuzzleCondition();

	@Override
	public GlobalInstruction getGlobalInstruction() {
		return globalInstruction;
	}

	@Override
	public String getConditionType() {
		return "WONPUZZLE->";
	}
	
	public void registerValue(PuzzlerObject puzzlerObject) {
		GoalObject puzzlerPos = new GoalObject(puzzlerObject.getX(), puzzlerObject.getY(), true);
		String value = puzzlerPos.getX() + "-" + puzzlerPos.getY();

		analyse(getConditionType() + value);
	}
	
	////////// OPTIMIZATION ////////////

	private static GameList<PiranhaObject> exceptions = new GameList<>(LISTTYPE.ARRAY);

	@Override
	public GameList<PiranhaObject> getExceptionsList() {
		return exceptions;
	}
	
}
