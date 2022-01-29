package com.sunsigne.reversedrebecca.pattern.render;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class TextDecoration {

	public void drawCenteredString(Graphics g, String text, Font font, Color text_color, int[] rect) {

		FontMetrics metrics = g.getFontMetrics(font);
		int x = rect[0] + (rect[2] - metrics.stringWidth(text)) / 2;
		int y = rect[1] + ((rect[3] - metrics.getHeight()) / 2) + metrics.getAscent();
		g.setColor(text_color);
		g.setFont(font);
		g.drawString(text, x, y);
	}

	public void drawShadowedString(Graphics g, String text, Font font, Color text_color, Color shadow_color,
			int[] rect) {

		int gap = 4;
		int[] shadow_rect = { rect[0] + gap, rect[1] + gap, rect[2], rect[3] };
		
		new TextDecoration().drawCenteredString(g, text, font, shadow_color, shadow_rect);
		new TextDecoration().drawCenteredString(g, text, font, text_color, rect);
	}

}
