package com.sunsigne.reversedrebecca.puzzle.cookie.difficulty;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.puzzle.cookie.upgrade.COOKIE_UPGRADE;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListenerBoolean;
import com.sunsigne.reversedrebecca.puzzle.cookie.CookiePuzzle;

public class EasierCookiePuzzle extends CookiePuzzle {

	public EasierCookiePuzzle(ToolPlayer toolPlayer, GenericListenerBoolean actionOnWinning,
			GenericListener actionOnLosing) {
		super(toolPlayer, actionOnWinning, actionOnLosing);
	}

	////////// PUZZLE ////////////

	@Override
	public void createPuzzle() {
		createCookie();
		createCounter(100);
		
		createUpgrade(COOKIE_UPGRADE.CURSOR, COOKIE_UPGRADE.COOKIE, 10, false);
	}

}
