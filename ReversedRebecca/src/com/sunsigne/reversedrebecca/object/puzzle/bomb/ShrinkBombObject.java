package com.sunsigne.reversedrebecca.object.puzzle.bomb;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.pattern.render.TextDecoration;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.font.FontTask;
import com.sunsigne.reversedrebecca.system.Size;

public class ShrinkBombObject extends FastBombObject {

	public ShrinkBombObject(Puzzle puzzle, boolean critical, int x, int y) {
		super(puzzle, critical, x + Size.S, y + Size.S, Size.S, Size.S);

		ymin = getPuzzle().getRow(1) + Size.S;
		ymax = getPuzzle().getRow(5) + 2 * Size.S;

		growing = new RandomGenerator().getBoolean();
		size = new RandomGenerator().getIntBetween(sizeMin, sizeMax);
	}

	////////// NAME ////////////
	
	@Override
	protected String getName(){
		return "BOMB SHRINK";
	}
	
	////////// SIZE ////////////

	@Override
	public int[] getRect() {
		int[] rect = new int[4];
		rect[0] = getX() - 2 * size;
		rect[1] = getY() - 2 * size;
		rect[2] = getWidth() + 4 * size;
		rect[3] = getHeight() + 4 * size;
		return rect;
	}

	////////// TICK ////////////

	private final int sizeMin = Size.XS / 3;
	private final int sizeMax = Size.XS;
	private int size;
	private boolean growing;

	@Override
	public void tick() {
		super.tick();

		if (!hasExploded())
			shrinkAndGrow();
	}

	private void shrinkAndGrow() {

		if (size > sizeMax)
			growing = false;

		if (size < sizeMin)
			growing = true;

		if (growing)
			size++;
		if (!growing)
			size--;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX() - 2 * size, getY() - 2 * size, getWidth() + 4 * size,
				getHeight() + 4 * size, null);

		Font font = new FontTask().createNewFont("DigitalNumbers-Regular.ttf", 32f + 2 * size);

		if (getCount() > 0 && isCritical() == false)
			drawCount(font, g);
	}

	private void drawCount(Font font, Graphics g) {

		int rect[] = new int[] { getX() - (size / 4), getY() + (2 + size / 2), getWidth(), getHeight() };

		if (getCount() == 1)
			rect = new int[] { getX() - (8 + size / 2), getY() + (2 + size / 2), getWidth(), getHeight() };

		new TextDecoration().drawOutlinesString(g, font, String.valueOf(getCount()), getTextColor(), Color.BLACK,
				DIRECTION.NULL, rect);
	}

}
