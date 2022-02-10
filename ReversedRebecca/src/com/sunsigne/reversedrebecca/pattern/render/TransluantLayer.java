package com.sunsigne.reversedrebecca.pattern.render;

import java.awt.Color;
import java.awt.Graphics;

public class TransluantLayer {

	public void drawGray(Graphics g, int width, int height) {
		Color gray = new Color(64, 64, 64, 204);
		g.setColor(gray);
		g.fillRect(0, 0, width, height);
	}

}
