package com.sunsigne.reversedrebecca.object.puzzle.dig.obstacle;

import com.sunsigne.reversedrebecca.object.puzzle.dig.tool.DIG_STATE;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;

public class BushObject extends BuriedObstacleObject {

	public BushObject(Puzzle puzzle, int w, int h) {
		super(puzzle, w, h);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "BUSH";
	}

	////////// MOUSE ////////////

	@Override
	public DIG_STATE getState() {
		return DIG_STATE.SLASH;
	}

	@Override
	protected String getSuccessSound() {
		return "slash_bush";
	}

	@Override
	protected String getFailSound() {
		return "bush_fail";
	}

	////////// TEXTURE ////////////
	
	@Override
	public int getSheetColCriterion() {
		return 3;
	}

}
