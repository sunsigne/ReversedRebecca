package com.sunsigne.reversedrebecca.instructions;

import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.characteristics.PathSearcher;
import com.sunsigne.reversedrebecca.object.characteristics.Position;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.npc.NPC;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject;

public class Statement {

	////////// TIME ////////////

	public void time(int time) {
		String statement = "TIME->";

		String value = String.valueOf(time);

		new MassiveInstruction(statement + value);
	}

	////////// GOAL ////////////

	public void goalReached(PathSearcher searcher, Position goal) {
		if (searcher instanceof NPC == false)
			return;

		String statement = "GOAL->";

		GoalObject pos = new GoalObject(goal.getX(), goal.getY(), true);
		String value = pos.getX() + "-" + pos.getY();

		new InstructionAnalyzer((NPC) searcher, statement + value);
	}

	////////// PUZZLE ////////////

	public void puzzleWon(PuzzlerObject puzzlerObject) {
		String statement = "WONPUZZLE->";

		GoalObject puzzlerPos = new GoalObject(puzzlerObject.getX(), puzzlerObject.getY(), true);
		String value = puzzlerPos.getX() + "-" + puzzlerPos.getY();

		new MassiveInstruction(statement + value);
	}

	////////// CHAT ////////////

	public void chatFinished(String value) {
		String statement = "TALK->";

		new MassiveInstruction(statement + value);
	}

}
