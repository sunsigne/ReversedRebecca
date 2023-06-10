package com.sunsigne.reversedrebecca.pattern.render;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class RectDecoration {

	private Color color = new Color(255, 232, 170);

	public enum RECTSIZE {
		X_SMALL, SMALL, NORMAL, LARGE, CUSTOM_BACK_BUTTON, CUSTOM_RESET_BUTTON, CUSTOM_NO_BUTTON;
	}

	private int[] getRect(int[] rect, RECTSIZE rectType) {
		int gap = 0;
		int arc = 30;

		if(rectType == null) {
			arc = 10;
			rectType = RECTSIZE.NORMAL;
		}
		
		switch (rectType) {
		case X_SMALL:
			gap = rect[2] / 8;
			arc = 10;
			rect = new int[] { rect[0] + gap / 2 + 4, rect[1] + 8, rect[2] - gap, rect[3] - 11, arc };
			break;
		case SMALL:
			gap = rect[3] / 3;
			rect = new int[] { rect[0], rect[1] + gap / 2, rect[2], rect[3] - gap, arc };
			break;
		case NORMAL:
			rect = new int[] { rect[0], rect[1], rect[2], rect[3], arc };
			break;
		case LARGE:
			gap = rect[3] / 6;
			rect = new int[] { rect[0] - gap / 2, rect[1] - gap / 2, rect[2] + gap, rect[3] + gap, arc };
			break;
		//////////////////////////////////////////
		case CUSTOM_BACK_BUTTON:
			gap = rect[2] / 8;
			arc = 10;
			rect = new int[] { rect[0] + gap / 2 + 12, rect[1] + gap / 2 + 14, rect[2] - gap - 4, rect[3] - gap - 20, arc };
			break;
		case CUSTOM_RESET_BUTTON:
			gap = rect[2] / 8;
			arc = 10;
			rect = new int[] { rect[0] + gap + 24, rect[1] + gap / 2 + 14, rect[2] - 3*gap + 2, rect[3] - gap - 20, arc };
			break;
		case CUSTOM_NO_BUTTON:
			rect = new int[] { 0, 0, 0, 0, arc};
		default:
			rect = new int[] { rect[0], rect[1], rect[2], rect[3], arc };
			break;
		}

		return rect;
	}

	public void drawRoundRect(Graphics g, int[] rect, RECTSIZE rectType) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(4));
		g2d.setColor(color);

		rect = getRect(rect, rectType);
		g2d.drawRoundRect(rect[0], rect[1], rect[2], rect[3], rect[4], rect[4]);
	}

}
