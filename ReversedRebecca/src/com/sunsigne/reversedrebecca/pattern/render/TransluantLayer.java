package com.sunsigne.reversedrebecca.pattern.render;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class TransluantLayer {

	public void draw(Graphics g, int width, int height) {
		Graphics2D g2d = (Graphics2D) g;
		Color color = new Color(64, 64, 64);
		g2d.setColor(color);

		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
		g2d.fillRect(0, 0, width, height);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}
	
}
