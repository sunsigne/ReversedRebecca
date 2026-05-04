package com.sunsigne.reversedrebecca.puzzle.yy.intelligence;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;

public class AnyYYIntelligencePuzzle extends YYIntelligencePuzzle {

	public AnyYYIntelligencePuzzle(ToolPlayer toolPlayer, GenericListener actionOnWinning,
			GenericListener actionOnLosing) {
		super(toolPlayer, actionOnWinning, actionOnLosing);
	}

	////////// PUZZLE ////////////

	@Override
	public void createPuzzle() {
		createChessBoard();
		createLauncher();
	}

}
