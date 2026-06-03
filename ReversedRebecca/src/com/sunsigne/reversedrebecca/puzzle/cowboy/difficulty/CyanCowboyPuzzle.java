package com.sunsigne.reversedrebecca.puzzle.cowboy.difficulty;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListenerBoolean;
import com.sunsigne.reversedrebecca.puzzle.cowboy.CowboyPuzzle;

public class CyanCowboyPuzzle extends CowboyPuzzle {

	public CyanCowboyPuzzle(ToolPlayer toolPlayer, GenericListenerBoolean actionOnWinning,
			GenericListener actionOnLosing) {
		super(toolPlayer, actionOnWinning, actionOnLosing);
	}

	////////// PUZZLE ////////////

	@Override
	public void createPuzzle() {
		createTarget();
		createRebecca();
		createBadGuy();
		createCursor();
	}

}
