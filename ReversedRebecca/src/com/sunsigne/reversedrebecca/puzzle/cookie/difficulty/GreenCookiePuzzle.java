package com.sunsigne.reversedrebecca.puzzle.cookie.difficulty;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListenerBoolean;
import com.sunsigne.reversedrebecca.puzzle.cookie.CookiePuzzle;

public class GreenCookiePuzzle extends CookiePuzzle {

	public GreenCookiePuzzle(ToolPlayer toolPlayer, GenericListenerBoolean actionOnWinning,
			GenericListener actionOnLosing) {
		super(toolPlayer, actionOnWinning, actionOnLosing);
	}

	////////// PUZZLE ////////////

	@Override
	public void createPuzzle() {
		createCookie();
		createCounter(10000);
	}

}
