package com.sunsigne.reversedrebecca.object.puzzle.dig;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.dig.DIG_STATE;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;

public class RockObject extends BuriedObstacleObject implements TickFree {

	public RockObject(Puzzle puzzle, int w, int h) {
		super(puzzle, w, h);
	}

	////////// NAME ////////////

	protected String getName() {
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
