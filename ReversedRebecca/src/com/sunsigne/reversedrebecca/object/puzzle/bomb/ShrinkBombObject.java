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

public class ShrinkBombObject extends MovingBombObject {

	public ShrinkBombObject(Puzzle puzzle, int x, int y) {
		super(puzzle, x + Size.S, y + Size.S, Size.S, Size.S);

		ymin = getPuzzle().getRow(1) + Size.S;
		ymax = getPuzzle().getRow(5) + 2 * Size.S;

		speed = Size.XS / 3;
		setVelY(new RandomGenerator().getBoolean() ? speed : -speed);

		growing = new RandomGenerator().getBoolean();
		size = new RandomGenerator().getIntBetween(sizeMin, sizeMax);
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

	private final int sizeMin = 0;
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
	// WARNING ! VisibleHitboxMode does't show correctly this hitbox
	public void render(Graphics g) {
		g.drawImage(getImage(), getX() - 2 * size, getY() - 2 * size, getWidth() + 4 * size,
				getHeight() + 4 * size, null);

		Font font = new FontTask().createNewFont("DigitalNumbers-Regular", 32f + 2 * size);

		if (getCount() > 0)
			drawCount(font, g);
	}

	private void drawCount(Font font, Graphics g) {

		int rect[] = new int[] { getX() - (size / 4), getY() + (2 + size / 2), getWidth(), getHeight() };

		if (getCount() == 1)
			rect = new int[] { getX() - (8 + size / 2), getY() + (2 + size / 2), getWidth(), getHeight() };

		new TextDecoration().drawOutlinesString(g, String.valueOf(getCount()), font, getTextColor(), Color.BLACK,
				DIRECTION.NULL, rect);
	}

}
