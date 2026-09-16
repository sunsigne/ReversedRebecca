package com.sunsigne.reversedrebecca.object.puzzle.cookie.upgrade;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;

public class CookieUpgradeCursorObject extends CookieUpgradeObject {

	public CookieUpgradeCursorObject(Puzzle puzzle, int x, int y) {
		super(puzzle, x, y);
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
		return 1;
	}

	@Override
	public COOKIE_UPGRADE getCostType() {
		return COOKIE_UPGRADE.COOKIE;
	}

	@Override
	public int getCost() {
		return 10;
	}

	////////// TEXTURE ////////////

	@Override
	public int getSheetRowCriterion() {
		return 4;
	}

}
