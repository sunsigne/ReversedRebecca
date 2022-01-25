package com.sunsigne.reversedrebecca.object.buttons;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.sunsigne.reversedrebecca.pattern.GenericListener;
import com.sunsigne.reversedrebecca.ressources.font.FontTask;

public class TitleScreenButton extends ButtonObject {

	public TitleScreenButton(String text, int x, int y, int w, int h, GenericListener onPress,
			GenericListener onRelease) {
		super(text, x, y, w, h, onPress, onRelease);
	}
	
	////////// RENDER ////////////
	
	private Font font = new FontTask().createNewFont("dogicabold.ttf", 66f);

	@Override
	public void render(Graphics g) {
		int x = getX() + 15;
		int y = getY() + 72;
		
		g.setColor(new Color(255, 163, 0, 80));
		g.setFont(font);
		g.drawString(getText(), x + 4, y + 4);

		g.setColor(new Color(255, 204, 0));
		g.setFont(font);
		g.drawString(getText(), x, y);
	}

}
