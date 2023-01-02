package com.sunsigne.reversedrebecca.puzzle.dig.difficulty;

import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.dig.DIG_STATE;
import com.sunsigne.reversedrebecca.puzzle.dig.DigPuzzle;
import com.sunsigne.reversedrebecca.system.Size;

public class YellowDigPuzzle extends DigPuzzle {

	public YellowDigPuzzle(int criticalChance, GenericListener actionOnWinning) {
		super(criticalChance, actionOnWinning);
	}

	@Override
	public int getSize() {
		return 2 * Size.M;
	}

	////////// PUZZLE ////////////

	@Override
	public void createPuzzle() {
		createDirtGrid();
		createRock(15);
		
		createTool(2, 1, DIG_STATE.HAND);
		createTool(2, 3, DIG_STATE.DIG);		
		createBuriedTool(2, 5, DIG_STATE.PICK);
		
		createExit();
	}

	private void createDirtGrid() {
		createDirt(7, 1);
		createDirt(8.5f, 1);
		createDirt(10, 1);
		createDirt(11.5f, 1);

		createDirt(7, 2.5f);
		createDirt(8.5f, 2.5f);
		createDirt(10, 2.5f);
		createDirt(11.5f, 2.5f);
		
		createDirt(7, 4);
		createDirt(8.5f, 4);
		createDirt(10, 4);
		createDirt(11.5f, 4);
		
		createDirt(7, 5.5f);
		createDirt(8.5f, 5.5f);
		createDirt(10, 5.5f);
		createDirt(11.5f, 5.5f);
	}

}
