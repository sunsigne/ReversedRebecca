package com.sunsigne.reversedrebecca.pattern.render;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.system.Window;

public class TextDecoration {

	///// center /////

	public void drawCenteredString(Graphics g, Font font, String text, Color color, DIRECTION facing, int[] rect) {
		if(text == null)
			return;
		
		FontMetrics metrics = g.getFontMetrics(font);

		int horizontal = 0;
		int vertical = 0;

		switch (facing) {
		case LEFT:
			// left and centered
			horizontal = 0;
			vertical = ((rect[3] - metrics.getHeight()) / 2) + metrics.getAscent();
			break;
		case RIGHT:
			// right and centered
			horizontal = rect[2] - metrics.stringWidth(text);
			vertical = ((rect[3] - metrics.getHeight()) / 2) + metrics.getAscent();
			break;
		case UP:
			// centered and up
			horizontal = (rect[2] - metrics.stringWidth(text)) / 2;
			vertical = 0;
			break;
		case DOWN:
			// centered and down
			horizontal = (rect[2] - metrics.stringWidth(text)) / 2;
			vertical = rect[3] - metrics.getHeight() + metrics.getAscent();
			break;

		case NULL :
			// centered
			horizontal = (rect[2] - metrics.stringWidth(text)) / 2;
			vertical = ((rect[3] - metrics.getHeight()) / 2) + metrics.getAscent();
			break;

		}

		int x = rect[0] + horizontal;
		int y = rect[1] + vertical;
		g.setColor(color);
		g.setFont(font);
		g.drawString(text, x, y);
	}

	///// shadow /////

	public void drawShadowedString(Graphics g, Font font, String text, Color color, Color shadow_color,
			DIRECTION facing, int[] rect) {

		int gap = (int) (4 * Math.sqrt(Window.SCALE_X));
		int[] shadow_rect = { rect[0] + gap, rect[1] + gap, rect[2], rect[3] };

		drawCenteredString(g, font, text, shadow_color, facing, shadow_rect);
		drawCenteredString(g, font, text, color, facing, rect);
	}

	///// outlines /////

	public void drawOutlinesString(Graphics g, Font font, String text, DIRECTION facing, int[] rect) {

		drawOutlinesString(g, font, text, Color.WHITE, Color.BLACK, facing, rect);
	}

	public void drawOutlinesString(Graphics g, Font font, String text, Color color, Color outlines_color,
			DIRECTION facing, int[] rect) {

		int gap = 2;
		int[] left_rect = { rect[0] - gap, rect[1], rect[2], rect[3] };
		int[] up_left_rect = { rect[0] - gap, rect[1] - gap, rect[2], rect[3] };
		int[] up_rect = { rect[0], rect[1] - gap, rect[2], rect[3] };
		int[] up_right_rect = { rect[0] + gap, rect[1] - gap, rect[2], rect[3] };
		int[] right_rect = { rect[0] + gap, rect[1], rect[2], rect[3] };
		int[] down_right_rect = { rect[0] + gap, rect[1] + gap, rect[2], rect[3] };
		int[] down_rect = { rect[0], rect[1] + gap, rect[2], rect[3] };
		int[] down_left_rect = { rect[0] - gap, rect[1] + gap, rect[2], rect[3] };

		drawCenteredString(g, font, text, outlines_color, facing, left_rect);
		drawCenteredString(g, font, text, outlines_color, facing, up_left_rect);
		drawCenteredString(g, font, text, outlines_color, facing, up_rect);
		drawCenteredString(g, font, text, outlines_color, facing, up_right_rect);
		drawCenteredString(g, font, text, outlines_color, facing, right_rect);
		drawCenteredString(g, font, text, outlines_color, facing, down_right_rect);
		drawCenteredString(g, font, text, outlines_color, facing, down_rect);
		drawCenteredString(g, font, text, outlines_color, facing, down_left_rect);

		drawCenteredString(g, font, text, color, facing, rect);
	}

}
