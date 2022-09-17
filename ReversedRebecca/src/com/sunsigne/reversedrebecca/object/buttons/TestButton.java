package com.sunsigne.reversedrebecca.object.buttons;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;

public class TestButton extends ButtonObject {

	public TestButton(int x, int y, int w, int h, GenericListener onPress, GenericListener onRelease) {
		super("TEST", x, y, w, h, onPress, onRelease);
	}

	////////// RENDER ////////////
		
	@Override
	public void render(Graphics g) {
		Font font = new Font("arial", 1, getHeight());
		g.setFont(font);
		g.setColor(Color.white);
		g.drawString(getText(), getX(), getY() + getHeight());
	}

}
