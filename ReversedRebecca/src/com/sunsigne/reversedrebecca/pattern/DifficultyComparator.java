package com.sunsigne.reversedrebecca.pattern;

import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;

public class DifficultyComparator {

	public boolean canUseTool(LVL puzzle_lvl, LVL tool_lvl) {

		switch (tool_lvl) {

		case RED:
			if (puzzle_lvl == LVL.RED)
				return true;
		case ORANGE:
			if (puzzle_lvl == LVL.ORANGE)
				return true;
		case YELLOW:
			if (puzzle_lvl == LVL.YELLOW)
				return true;
		case GREEN:
			if (puzzle_lvl == LVL.GREEN)
				return true;
		case CYAN:
			if (puzzle_lvl == LVL.CYAN)
				return true;
		case NULL:
			return puzzle_lvl == LVL.NULL;

		}
		return false;
	}

	public boolean isPositiveUpgade(LVL tool_player_lvl, LVL tool_lvl) {
		return new DifficultyComparator().canUseTool(tool_player_lvl, tool_lvl);
	}

	public boolean isForbiddenUpgrade(LVL max_lvl, LVL lvl) {

		switch (lvl) {

		case NULL:
			if (max_lvl == LVL.NULL)
				return false;
		case CYAN:
			if (max_lvl == LVL.CYAN)
				return false;
		case GREEN:
			if (max_lvl == LVL.GREEN)
				return false;
		case YELLOW:
			if (max_lvl == LVL.YELLOW)
				return false;
		case ORANGE:
			if (max_lvl == LVL.ORANGE)
				return false;
		case RED:
			if (max_lvl == LVL.RED)
				return false;

		}
		return true;
	}

}
