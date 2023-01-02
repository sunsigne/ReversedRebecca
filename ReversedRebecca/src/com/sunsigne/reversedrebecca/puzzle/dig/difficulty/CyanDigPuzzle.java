package com.sunsigne.reversedrebecca.puzzle.dig.difficulty;

import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.dig.DIG_STATE;
import com.sunsigne.reversedrebecca.puzzle.dig.DigPuzzle;
import com.sunsigne.reversedrebecca.system.Size;

public class CyanDigPuzzle extends DigPuzzle {

	public CyanDigPuzzle(int criticalChance, GenericListener actionOnWinning) {
		super(criticalChance, actionOnWinning);
	}

	@Override
	public int getSize() {
		return 2 * Size.L;
	}

	////////// PUZZLE ////////////

	@Override
	public void createPuzzle() {
		createDirtGrid();
		createRock(2);
		
		createTool(2, 2, DIG_STATE.DIG);
		createTool(2, 4, DIG_STATE.PICK);	
		
		createExit();
	}

	private void createDirtGrid() {
		createDirt(7, 1);
		createDirt(9, 1);
		createDirt(11, 1);

		createDirt(7, 3);
		createDirt(9, 3);
		createDirt(11, 3);

		createDirt(7, 5);
		createDirt(9, 5);
		createDirt(11, 5);
	}

}
