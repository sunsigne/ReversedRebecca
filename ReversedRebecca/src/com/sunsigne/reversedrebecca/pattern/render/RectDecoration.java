package com.sunsigne.reversedrebecca.pattern.render;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class RectDecoration {

	private Color color = new Color(255, 232, 170);

	public enum RECTSIZE {
		SMALL, NORMAL, LARGE;
	}

	private int[] getRect(int[] rect, RECTSIZE rectType) {
		int gap = 0;

		switch (rectType) {
		case SMALL:
			gap = rect[3] / 3;
			rect = new int[] { rect[0], rect[1] + gap / 2, rect[2], rect[3] - gap };
			break;
		case NORMAL:
			break;
		case LARGE:
			gap = rect[3] / 6;
			rect = new int[] { rect[0] - gap / 2, rect[1] - gap / 2, rect[2] + gap, rect[3] + gap };
			break;
		default:
			break;
		}

		return rect;
	}

	public void drawRoundRect(Graphics g, int[] rect, RECTSIZE rectType) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(4));
		g2d.setColor(color);

		rect = getRect(rect, rectType);
		g2d.drawRoundRect(rect[0], rect[1], rect[2], rect[3], 30, 30);
	}

}
