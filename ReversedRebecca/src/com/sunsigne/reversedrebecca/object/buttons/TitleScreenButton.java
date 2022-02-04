package com.sunsigne.reversedrebecca.object.buttons;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.pattern.GenericListener;
import com.sunsigne.reversedrebecca.pattern.MousePos;
import com.sunsigne.reversedrebecca.pattern.render.TextDecoration;
import com.sunsigne.reversedrebecca.ressources.font.FontTask;

public class TitleScreenButton extends ButtonObject {

	public TitleScreenButton(String text, int x, int y, int w, int h, GenericListener onPress,
			GenericListener onRelease) {
		super(text.toUpperCase(), x, y, w, h, onPress, onRelease);
	}

	////////// RENDER ////////////

	private Font font = new FontTask().createNewFont("dogicabold", 66f);

	@Override
	public void render(Graphics g) {

		Color text_color = new Color(255, 204, 0);
		Color shadow_color = new Color(255, 163, 0, 80);
		int[] rect = getRect();
		
		if (mouseOver(new MousePos().get(), getRect()) && isClickable())
		{
			text_color = new Color(255, 232, 170);
			rect = new int[] {getX(), getY() - 3, getWidth(), getHeight()};
		}

		new TextDecoration().drawShadowedString(g, getText(), font, text_color, shadow_color, getFacing(), rect);
	}

}
