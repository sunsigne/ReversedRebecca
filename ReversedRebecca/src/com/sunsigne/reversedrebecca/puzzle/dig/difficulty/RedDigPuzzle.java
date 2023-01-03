package com.sunsigne.reversedrebecca.puzzle.dig.difficulty;

import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.dig.DIG_STATE;
import com.sunsigne.reversedrebecca.puzzle.dig.DigPuzzle;
import com.sunsigne.reversedrebecca.system.Size;

public class RedDigPuzzle extends DigPuzzle {

	public RedDigPuzzle(int criticalChance, GenericListener actionOnWinning) {
		super(criticalChance, actionOnWinning);
	}

	@Override
	public int getSize() {
		return Size.L;
	}

	////////// PUZZLE ////////////

	@Override
	public void createPuzzle() {
		createDirtGrid();
		createRock(15);
		createLog(10);

		createTool(1, 2, DIG_STATE.HAND);
		createTool(3, 2, DIG_STATE.DIG);

		if (new RandomGenerator().getBoolean()) {
			createBuriedTool(1, 4, DIG_STATE.PICK);
			createBuriedTool(3, 4, DIG_STATE.CHOP);
		} else {
			createBuriedTool(3, 4, DIG_STATE.CHOP);
			createBuriedTool(1, 4, DIG_STATE.PICK);
		}

		createExit();
	}

	private void createDirtGrid() {
		for (int row = 1; row <= 6; row++) {
			createDirt(7, row);
			createDirt(8, row);
			createDirt(9, row);
			createDirt(10, row);
			createDirt(11, row);
			createDirt(12, row);
		}
	}

}
