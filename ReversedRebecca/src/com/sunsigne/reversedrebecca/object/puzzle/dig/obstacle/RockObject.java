package com.sunsigne.reversedrebecca.object.puzzle.dig.obstacle;

import com.sunsigne.reversedrebecca.object.puzzle.dig.tool.DIG_STATE;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;

public class RockObject extends BuriedObstacleObject {

	public RockObject(Puzzle puzzle, int w, int h) {
		super(puzzle, w, h);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "ROCK";
	}

	////////// MOUSE ////////////

	@Override
	public DIG_STATE getState() {
		return DIG_STATE.PICK;
	}

	@Override
	protected String getSuccessSound() {
		return "pick_rock";
	}

	@Override
	protected String getFailSound() {
		return "pick_fail";
	}

}
