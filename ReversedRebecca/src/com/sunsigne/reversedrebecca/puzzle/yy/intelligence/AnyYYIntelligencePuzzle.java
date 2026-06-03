package com.sunsigne.reversedrebecca.puzzle.yy.intelligence;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListenerBoolean;

public class AnyYYIntelligencePuzzle extends YYIntelligencePuzzle {

	public AnyYYIntelligencePuzzle(ToolPlayer toolPlayer, GenericListenerBoolean actionOnWinning,
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
