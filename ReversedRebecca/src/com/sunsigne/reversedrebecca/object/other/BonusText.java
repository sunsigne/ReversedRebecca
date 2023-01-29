package com.sunsigne.reversedrebecca.object.other;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.pattern.render.TextDecoration;
import com.sunsigne.reversedrebecca.ressources.font.FontTask;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.Size;

public class BonusText extends GameObject {

	public BonusText(String text, int x, int y) {
		this(text, x, y, false);
	}
	
	public BonusText(String text, int x, int y, boolean importante) {
		super(x, y);
		this.text = text;

		createFont(importante);
		createColor(importante);
		setVelY(-1);
	}

	////////// NAME ////////////

	@Override
	public String toString() {
		var clazz = "BONUS TEXT";
		return clazz + " : " + text.toUpperCase();
	}

	////////// TEXT ////////////

	private String text;
	private Font font;
	
	private void createFont(boolean importante) {
		float size = importante ? 44f: 22f;
		font = new FontTask().createNewFont("square_sans_serif_7.ttf", size);
	}

	private Color color;
	private Color outlines_color;
	
	private void createColor(boolean importante) {
		color = importante ? Color.BLACK : Color.WHITE;
		outlines_color = importante ? Color.WHITE : Color.BLACK;
	}

	////////// TICK ////////////

	private int time = 40;

	@Override
	public void tick() {
		--time;
		if (time <= 0)
			LAYER.WORLD_TEXT.getHandler().removeObject(this);
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {

		int[] rect = new int[] { getX(), getY() - Size.S, getWidth(), getHeight() };
		new TextDecoration().drawOutlinesString(g, font, text, color, outlines_color, DIRECTION.NULL, rect);
	}

}
