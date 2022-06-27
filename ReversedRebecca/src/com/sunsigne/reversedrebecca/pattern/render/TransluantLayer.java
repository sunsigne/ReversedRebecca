package com.sunsigne.reversedrebecca.pattern.render;

import java.awt.Color;
import java.awt.Graphics;

import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.Window;

public class TransluantLayer {

	public void drawGray(Graphics g, int width, int height) {
		Color gray = new Color(64, 64, 64, 204);
		g.setColor(gray);
		g.fillRect(0, 0, width, height);
	}
	
	public void drawDarkGray(Graphics g, int width, int height) {
		Color gray = new Color(40, 40, 40, 224);
		g.setColor(gray);
		g.fillRect(0, 0, width, height);
	}

	public void drawPuzzle(Graphics g, Color color) {
		g.setColor(color);
		g.fillRect(Size.L, Size.L, Window.WIDHT - 2 * Size.L, Window.HEIGHT - 2 * Size.L);
	}

}
