package com.sunsigne.reversedrebecca.object.puzzle.cookie.upgrade;

import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;

public class CookieUpgradeAntiGObject extends CookieUpgradeObject {

	public CookieUpgradeAntiGObject(Puzzle puzzle, int x, int y, COOKIE_UPGRADE unlockingType, int unlockingAt, boolean nerfed) {
		super(puzzle, x, y, unlockingType, unlockingAt, nerfed);
	}

	////////// UPGRADE ////////////

	@Override
	public COOKIE_UPGRADE getType() {
		return COOKIE_UPGRADE.ANTI_G;
	}

	@Override
	public COOKIE_UPGRADE getAmountType() {
		return COOKIE_UPGRADE.GRANDPA;
	}
	
	@Override
	public int getAmountBySecond() {
		return 3;
	}

	@Override
	public COOKIE_UPGRADE getCostType() {
		return COOKIE_UPGRADE.FACTORY;
	}

	@Override
	public int getInitialCost() {
		return 7;
	}

	@Override
	public GenericListener getUnlockingAction() {
		return null;
	}
	
}
