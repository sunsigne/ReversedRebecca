package com.sunsigne.reversedrebecca.object.puzzle.cookie.upgrade;

import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;

public class CookieUpgradeStockObject extends CookieUpgradeObject {

	public CookieUpgradeStockObject(Puzzle puzzle, int x, int y, COOKIE_UPGRADE unlockingType, int unlockingAt, boolean nerfed) {
		super(puzzle, x, y, unlockingType, unlockingAt, nerfed);
	}

	////////// UPGRADE ////////////

	@Override
	public COOKIE_UPGRADE getType() {
		return COOKIE_UPGRADE.STOCK;
	}

	@Override
	public COOKIE_UPGRADE getAmountType() {
		return nerfed ? COOKIE_UPGRADE.CURSOR : COOKIE_UPGRADE.COOKIE;
	}
	
	@Override
	public int getAmountBySecond() {
		return nerfed ? 1 : 20;
	}

	@Override
	public COOKIE_UPGRADE getCostType() {
		return COOKIE_UPGRADE.COOKIE;
	}

	@Override
	public int getInitialCost() {
		return 2000;
	}

	@Override
	public GenericListener getUnlockingAction() {
		return null;
	}
	
}
