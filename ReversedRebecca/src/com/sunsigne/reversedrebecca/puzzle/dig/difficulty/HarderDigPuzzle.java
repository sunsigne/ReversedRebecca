package com.sunsigne.reversedrebecca.puzzle.dig.difficulty;

import com.sunsigne.reversedrebecca.object.puzzle.dig.tool.DIG_STATE;
import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.dig.DigPuzzle;
import com.sunsigne.reversedrebecca.system.Size;

public class HarderDigPuzzle extends DigPuzzle {

	public HarderDigPuzzle(int criticalChance, GenericListener actionOnWinning) {
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
		createObstacles();
		createTools();
		
		createExit();
		createExit();
		if (new RandomGenerator().getBoolean())
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

	private void createObstacles() {
		createRock(10);
		createLog(5);
	}

	private void createTools() {
		if (isCritical) {
			createTool(2, 3, DIG_STATE.CRITICAL);
			setState(DIG_STATE.CRITICAL);
			return;
		}
		
		createTool(1, 2, DIG_STATE.PUNCH);
		createBuriedShovel(1, 2);
		setState(DIG_STATE.PUNCH);

		if (new RandomGenerator().getBoolean()) {
			createBuriedTool(1, 4, DIG_STATE.PICK);
			createBuriedTool(3, 2, DIG_STATE.CHOP);
		} else {
			createBuriedTool(3, 2, DIG_STATE.CHOP);
			createBuriedTool(1, 4, DIG_STATE.PICK);
		}
	}

}
