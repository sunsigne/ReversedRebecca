package com.sunsigne.reversedrebecca.object.puzzle.dig.obstacle;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.dig.DIG_STATE;

public class LogObject extends BuriedObstacleObject {

	public LogObject(Puzzle puzzle, int w, int h) {
		super(puzzle, w, h);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "LOG";
	}

	////////// MOUSE ////////////

	@Override
	public DIG_STATE getState() {
		return DIG_STATE.CHOP;
	}

	@Override
	protected String getSuccessSound() {
		return "chop_log";
	}

	@Override
	protected String getFailSound() {
		return "chop_fail";
	}

}
