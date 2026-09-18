package com.sunsigne.reversedrebecca.object.puzzle.cookie.upgrade;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.system.Size;

public class CookieUpgradeStockObject extends CookieUpgradeObject {

	public CookieUpgradeStockObject(Puzzle puzzle, int x, int y, COOKIE_UPGRADE unlockingType, int unlockingAt,
			boolean nerfed) {
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

	////////// RENDER ////////////

	@Override
	protected void drawType(Graphics g) {
		if (nerfed)
			g.drawImage(getType().getImage(), getX(), getY() + Size.L, Size.L, - Size.L, null);
		else
			super.drawType(g);
	}
}
