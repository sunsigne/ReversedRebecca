package com.sunsigne.reversedrebecca.object.puzzle.cookie.upgrade;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;

public class CookieUpgradeGrandpaObject extends CookieUpgradeObject {

	public CookieUpgradeGrandpaObject(Puzzle puzzle, int x, int y) {
		super(puzzle, x, y);
	}

	////////// UPGRADE ////////////

	@Override
	public COOKIE_UPGRADE getType() {
		return COOKIE_UPGRADE.GRANDPA;
	}

	@Override
	public COOKIE_UPGRADE getAmountType() {
		return COOKIE_UPGRADE.COOKIE;
	}
	
	@Override
	public int getAmountBySecond() {
		return 3;
	}

	@Override
	public COOKIE_UPGRADE getCostType() {
		return COOKIE_UPGRADE.CURSOR;
	}

	@Override
	public int getInitialCost() {
		return 5;
	}

}
