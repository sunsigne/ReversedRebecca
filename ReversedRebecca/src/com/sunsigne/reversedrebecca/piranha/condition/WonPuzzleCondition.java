package com.sunsigne.reversedrebecca.piranha.condition;

import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject;
import com.sunsigne.reversedrebecca.piranha.MassiveInstruction;

public class WonPuzzleCondition extends MassiveInstruction {

	public void registerValue(PuzzlerObject puzzlerObject) {
		String conditionType = "WONPUZZLE->";

		GoalObject puzzlerPos = new GoalObject(puzzlerObject.getX(), puzzlerObject.getY(), true);
		String value = puzzlerPos.getX() + "-" + puzzlerPos.getY();

		analyse(conditionType + value);
	}
	
}
