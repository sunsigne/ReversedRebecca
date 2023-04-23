package com.sunsigne.reversedrebecca.object.buttons;

import java.awt.Color;
import java.awt.Graphics;

import com.sunsigne.reversedrebecca.pattern.render.RectDecoration;
import com.sunsigne.reversedrebecca.pattern.render.TextDecoration;
import com.sunsigne.reversedrebecca.system.controllers.ControllerManager;

public class TitleScreenTextDynamic extends TitleScreenText {

	public TitleScreenTextDynamic(String text, int x, int y) {
		this(text, x, y, 415, 80);
	}
	
	public TitleScreenTextDynamic(String text, int x, int y, int w, int h) {
		super(text, x, y, w, h);
	}
	
	////////// SOUND ////////////

	public String getSound() {
		return null;
	}

	////////// MOUSE ////////////

	@Override
	public boolean isClickable() {
		return true;
	}

	////////// RENDER ////////////
	
	@Override
	public void render(Graphics g) {
		Color text_color = new Color(255, 204, 0);
		Color shadow_color = new Color(255, 163, 0, 80);
		int[] rect = getRect();

		if (isSelected() && ControllerManager.getInstance().isUsingGamepad()) {
			new RectDecoration().drawRoundRect(g, rect, rectsize);
			text_color = new Color(255, 232, 170);
			rect = new int[] { getX(), getY() - 3, getWidth(), getHeight() };
		}			

		new TextDecoration().drawShadowedString(g, getFont(), getText(), text_color, shadow_color, getFacing(), rect);
	}
	
}
