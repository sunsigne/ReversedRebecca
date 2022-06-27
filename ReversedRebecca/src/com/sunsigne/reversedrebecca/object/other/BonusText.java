package com.sunsigne.reversedrebecca.object.other;

import java.awt.Font;
import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.pattern.render.TextDecoration;
import com.sunsigne.reversedrebecca.ressources.font.FontTask;
import com.sunsigne.reversedrebecca.system.Size;

public class BonusText extends GameObject {

	public BonusText(String text, int x, int y) {
		super(x, y);
		this.text = text;
		setVelY(-1);
	}

	private String text;

	////////// TICK ////////////

	private int time = 40;

	@Override
	public void tick() {
		--time;
		if (time <= 0)
			getHandler().removeObject(this);
	}

	////////// RENDER ////////////

	private Font font = new FontTask().createNewFont("square_sans_serif_7.ttf", 22f);

	@Override
	public void render(Graphics g) {

		int[] rect = new int[] { getX(), getY() - Size.S, getWidth(), getHeight() };
		new TextDecoration().drawOutlinesString(g, font, text, DIRECTION.NULL, rect);
	}

}
