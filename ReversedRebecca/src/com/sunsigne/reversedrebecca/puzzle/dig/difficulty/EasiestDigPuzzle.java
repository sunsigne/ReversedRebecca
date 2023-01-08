package com.sunsigne.reversedrebecca.puzzle.dig.difficulty;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.puzzle.dig.tool.DIG_STATE;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.dig.DigPuzzle;
import com.sunsigne.reversedrebecca.system.Size;

public class EasiestDigPuzzle extends DigPuzzle {

	public EasiestDigPuzzle(ToolPlayer toolPlayer, GenericListener actionOnWinning) {
		super(toolPlayer, actionOnWinning);
	}

	@Override
	public int getSize() {
		return 3 * Size.L;
	}

	////////// PUZZLE ////////////

	@Override
	public void createPuzzle() {
		createDirtGrid();
		createObstacles();
		createTools();
		createExit();
	}

	private void createDirtGrid() {
		createDirt(7, 1);
		createDirt(10, 1);

		createDirt(7, 4);
		createDirt(10, 4);
	}

	private void createObstacles() {

	}

	private void createTools() {
		if (isCritical) {
			createTool(2, 3, DIG_STATE.CRITICAL);
			setState(DIG_STATE.CRITICAL);
			return;
		}

		createTool(2, 3, DIG_STATE.DIG);
	}

}
