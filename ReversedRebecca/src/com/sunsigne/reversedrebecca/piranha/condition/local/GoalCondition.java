package com.sunsigne.reversedrebecca.piranha.condition.local;

import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.characteristics.PathSearcher;
import com.sunsigne.reversedrebecca.object.characteristics.Position;
import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.piranha.condition.LocalInstruction;

public class GoalCondition extends LocalInstruction {

	public void registerValue(PathSearcher searcher, Position goal) {
		if (searcher instanceof ExtraBehaviorsObject == false)
			return;

		String conditionType = "GOAL->";

		GoalObject pos = new GoalObject(goal.getX(), goal.getY(), true);
		String value = pos.getX() + "-" + pos.getY();

		analyse((ExtraBehaviorsObject) searcher, conditionType + value);
	}

}
