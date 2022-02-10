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

	public void drawPuzzle(Graphics g, Color color) {
		Color green = new Color(15, 45, 10, 240);
		g.setColor(green);
		g.fillRect(Size.L, Size.L, Window.WIDHT - 2 * Size.L, Window.HEIGHT - 2 * Size.L);

		Color light_gray = new Color(64, 64, 64, 128);
		g.setColor(light_gray);
		// up
//		g.fillRect(Size.L, 0, Window.WIDHT - 2 * Size.L, Size.L);
		// right
//		g.fillRect(Window.WIDHT - Size.L, 0, Size.L, Window.HEIGHT);
		// down
//		g.fillRect(Size.L, Window.HEIGHT - Size.L, Window.WIDHT - 2 * Size.L, Size.L);
		// left
//		g.fillRect(0, 0, Size.L, Window.HEIGHT);
	}

}
