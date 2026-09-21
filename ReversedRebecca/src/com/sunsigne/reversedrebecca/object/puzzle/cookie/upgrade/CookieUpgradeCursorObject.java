package com.sunsigne.reversedrebecca.object.puzzle.cookie.upgrade;

import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.cookie.CookiePuzzle;

public class CookieUpgradeCursorObject extends CookieUpgradeObject {

	public CookieUpgradeCursorObject(Puzzle puzzle, int x, int y, COOKIE_UPGRADE unlockingType, int unlockingAt,
			boolean nerfed) {
		super(puzzle, x, y, unlockingType, unlockingAt, nerfed);
	}

	////////// UPGRADE ////////////

	@Override
	public COOKIE_UPGRADE getType() {
		return COOKIE_UPGRADE.CURSOR;
	}

	@Override
	public COOKIE_UPGRADE getAmountType() {
		return COOKIE_UPGRADE.COOKIE;
	}

	@Override
	public int getAmountBySecond() {
		return nerfed ? 0 : 1;
	}

	@Override
	public COOKIE_UPGRADE getCostType() {
		return COOKIE_UPGRADE.COOKIE;
	}

	@Override
	public int getInitialCost() {
		return 10;
	}

	@Override
	public GenericListener getUnlockingAction() {
		GenericListener unlockingAction = () -> {
			getCounter(COOKIE_UPGRADE.COOKIE).setVelX(-10);
			getCounter(COOKIE_UPGRADE.COOKIE).setVelY(1);
			((CookiePuzzle) getPuzzle()).setPreset(CookiePuzzle.COOKIE, false);
		};

		return unlockingAction;
	}
}
