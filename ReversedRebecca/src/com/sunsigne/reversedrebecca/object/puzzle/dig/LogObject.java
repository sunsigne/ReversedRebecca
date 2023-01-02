package com.sunsigne.reversedrebecca.object.puzzle.dig;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.dig.DIG_STATE;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;

public class LogObject extends BuriedObstacleObject implements TickFree {

	public LogObject(Puzzle puzzle, int w, int h) {
		super(puzzle, w, h);
	}

	////////// NAME ////////////

	protected String getName() {
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
