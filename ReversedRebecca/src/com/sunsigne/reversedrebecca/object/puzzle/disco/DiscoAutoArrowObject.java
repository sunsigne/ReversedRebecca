package com.sunsigne.reversedrebecca.object.puzzle.disco;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.disco.DiscoPuzzle;

public class DiscoAutoArrowObject extends DiscoArrowObject {

	public DiscoAutoArrowObject(Puzzle puzzle, DIRECTION facing, int x, int y) {
		super(puzzle, facing, x, y);
	}

	////////// NAME ////////////

	protected String getName() {
		return "AUTO ARROW";
	}

	////////// PLAY ////////////

	protected void play(CASE caze) {
		DiscoPuzzle puzzle = (DiscoPuzzle) getPuzzle();
		puzzle.updateDiscoDancerFacing(getFacing());
		super.play(caze);
	}

	////////// TICK ////////////

	private boolean flag;
	private int time;
	private final int DELAY = 4;

	@Override
	public void tick() {
		if (onUp && onDown)
			flag = true;

		if (flag == false)
			return;

		time++;
		if (time < DELAY)
			return;

		if (new RandomGenerator().getBoolean(85))
			play(CASE.PERFECT);
		else
			play(CASE.GOOD);
	}

	////////// TEXTURE ////////////

	@Override
	public int getSheetRowCriterion() {
		return 4;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
	}

	////////// KEYBOARD ////////////

	@Override
	public void keyPressed(KeyEvent e) {

	}

}
