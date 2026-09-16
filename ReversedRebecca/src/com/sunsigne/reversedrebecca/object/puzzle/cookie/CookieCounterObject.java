package com.sunsigne.reversedrebecca.object.puzzle.cookie;

import java.awt.Font;
import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.pattern.render.TextDecoration;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.cookie.CookiePuzzle;
import com.sunsigne.reversedrebecca.ressources.font.FontTask;
import com.sunsigne.reversedrebecca.system.Size;

public class CookieCounterObject extends PuzzleObject {

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
		return PhysicLinker.PUZZLE;
	}

	////////// MAX COUNT ////////////

	private int maxCount;
	private int count;

	public int geCount() {
		return count;
	}

	public void addToCount(int amount) {
		count = count + amount;
	}

	////////// TICK ////////////

	private int time;

	@Override
	public void tick() {
		time++;

		if (count >= maxCount || maxCount <= 0)
			getPuzzle().closePuzzle(true);
	}

	////////// RENDER ////////////

	private Font font = new FontTask().createNewFont("startedbyamouse.ttf", 150f);

	@Override
	public void render(Graphics g) {
		if (time < ((CookiePuzzle) getPuzzle()).getDelayBeforeReady())
			return;

		String text = String.valueOf(count) + " / " + String.valueOf(maxCount);
		int rect[] = new int[] { getX() - 5, getY() + 20, getWidth(), getHeight() };
		new TextDecoration().drawOutlinesString(g, font, text, DIRECTION.NULL, rect);
	}

}
