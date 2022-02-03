package com.sunsigne.reversedrebecca.pattern;

import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;

public class DifficultyComparator {

	public boolean canUseTool(LVL tested_difficulty, LVL tool_lvl) {

		switch (tool_lvl) {

		case RED:
			if (tested_difficulty == LVL.RED)
				return true;
		case ORANGE:
			if (tested_difficulty == LVL.ORANGE)
				return true;
		case YELLOW:
			if (tested_difficulty == LVL.YELLOW)
				return true;
		case GREEN:
			if (tested_difficulty == LVL.GREEN)
				return true;
		case CYAN:
			if (tested_difficulty == LVL.CYAN)
				return true;
		case NULL:
			return tested_difficulty == LVL.NULL;

		}
		return false;
	}
	
}
