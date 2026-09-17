package com.sunsigne.reversedrebecca.object.puzzle.cookie;

import java.awt.Font;
import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.object.puzzle.cookie.upgrade.CookieUpgradeCursorObject;
import com.sunsigne.reversedrebecca.object.puzzle.cookie.upgrade.CookieUpgradeFactoryObject;
import com.sunsigne.reversedrebecca.object.puzzle.cookie.upgrade.CookieUpgradeGrandpaObject;
import com.sunsigne.reversedrebecca.object.puzzle.cookie.upgrade.CookieUpgradeObject;
import com.sunsigne.reversedrebecca.pattern.render.TextDecoration;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.cookie.CookiePuzzle;
import com.sunsigne.reversedrebecca.ressources.font.FontTask;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.Size;

public class CookieCounterObject extends PuzzleObject implements CookieCounting {

	public CookieCounterObject(Puzzle puzzle, int maxCount, int x, int y) {
		super(puzzle, false, x, y, 3 * Size.XL, 3 * Size.XL);
		this.maxCount = maxCount;
	}

	////////// NAME ////////////

	protected String getName() {
		return "COUNTER";
	}

	@Override
	public String toString() {
		return "PUZZLE : " + getName();
	}

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.PUZZLE_MOVER;
	}

	////////// COUNT ////////////

	private int maxCount;
	private float count;

	@Override
	public float getCount() {
		return count;
	}

	@Override
	public void addToCount(float amount) {
		count = count + amount;
	}

	////////// UPGRADE ////////////

	private CookieUpgradeObject cursorUpgrade;
	private boolean unlockCursor;
	private final int CURSOR = 10;

	public CookieUpgradeObject getCursorUpgrade() {
		return cursorUpgrade;
	}

	private CookieUpgradeObject grandpaUpgrade;
	private boolean unlockGrandpa;
	private final int GRANDPA = 5;

	public CookieUpgradeObject getGrandpaUpgrade() {
		return grandpaUpgrade;
	}

	private CookieUpgradeObject factoryUpgrade;
	private boolean unlockFactory;
	private final int FACTORY = 200;

	public CookieUpgradeObject getFactoryUpgrade() {
		return factoryUpgrade;
	}

	private void unlockingUpgrade() {
		int gap = 8;

		if (unlockCursor == false && getCount() >= CURSOR) {
			setVelX(-10);
			setVelY(1);
			unlockCursor = true;
			cursorUpgrade = new CookieUpgradeCursorObject(getPuzzle(), getPuzzle().getCol(8),
					getPuzzle().getRow(1) + gap);
			LAYER.PUZZLE.addObject(cursorUpgrade);
		}

		if (cursorUpgrade == null)
			return;

		if (unlockGrandpa == false && getCursorUpgrade().getCount() >= GRANDPA) {
			unlockGrandpa = true;
			grandpaUpgrade = new CookieUpgradeGrandpaObject(getPuzzle(), getPuzzle().getCol(8),
					getPuzzle().getRow(2) + 2 * gap);
			LAYER.PUZZLE.addObject(grandpaUpgrade);
		}

		if (grandpaUpgrade == null)
			return;

		if (unlockFactory == false && getCount() >= FACTORY) {
			unlockFactory = true;
			factoryUpgrade = new CookieUpgradeFactoryObject(getPuzzle(), getPuzzle().getCol(8),
					getPuzzle().getRow(3) + 3 * gap);
			LAYER.PUZZLE.addObject(factoryUpgrade);
		}
	}

	////////// TICK ////////////

	private int time;
	private final int XMIN = getPuzzle().getCol(2) + 16;

	@Override
	public void tick() {
		time++;
		unlockingUpgrade();

		if (getX() < XMIN) {
			setX(XMIN);
			setMotionless();
		}

		if (count >= maxCount || maxCount <= 0)
			getPuzzle().closePuzzle(true);
	}

	////////// RENDER ////////////

	private Font font = new FontTask().createNewFont("startedbyamouse.ttf", 150f);

	@Override
	public void render(Graphics g) {
		if (time < ((CookiePuzzle) getPuzzle()).getDelayBeforeReady())
			return;

		int rect[] = new int[] { getX() - 5, getY() + 20, getWidth(), getHeight() };
		new TextDecoration().drawOutlinesString(g, font, String.valueOf((int) count), DIRECTION.LEFT, rect);
		new TextDecoration().drawOutlinesString(g, font, "/", DIRECTION.NULL, rect);
		new TextDecoration().drawOutlinesString(g, font, String.valueOf(maxCount), DIRECTION.RIGHT, rect);
	}

}
