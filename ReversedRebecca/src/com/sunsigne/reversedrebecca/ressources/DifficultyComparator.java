package com.sunsigne.reversedrebecca.ressources;

import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;

public class DifficultyComparator {

	public boolean canUseTool(LVL puzzle_difficulty, LVL tool_difficulty) {

		switch (tool_difficulty) {

		case RED:
			if (puzzle_difficulty == LVL.RED)
				return true;
		case ORANGE:
			if (puzzle_difficulty == LVL.ORANGE)
				return true;
		case YELLOW:
			if (puzzle_difficulty == LVL.YELLOW)
				return true;
		case GREEN:
			if (puzzle_difficulty == LVL.GREEN)
				return true;
		case CYAN:
			if (puzzle_difficulty == LVL.CYAN)
				return true;
		case NULL:
			return puzzle_difficulty == LVL.NULL;

		}
		return false;
	}
	
}
