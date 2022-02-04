package com.sunsigne.reversedrebecca.pattern.render;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;

public class TextDecoration {

	///// center /////

	public void drawCenteredString(Graphics g, String text, Font font, Color text_color, DIRECTION facing, int[] rect) {

		FontMetrics metrics = g.getFontMetrics(font);

		int horizontal = 0;

		switch (facing) {
		case LEFT:
			horizontal = 0;
			break;
		case RIGHT:
			horizontal = (rect[2] - metrics.stringWidth(text));
			break;
		default:
			horizontal = (rect[2] - metrics.stringWidth(text)) / 2;
			break;
		}

		int x = rect[0] + horizontal;
		int y = rect[1] + ((rect[3] - metrics.getHeight()) / 2) + metrics.getAscent();
		g.setColor(text_color);
		g.setFont(font);
		g.drawString(text, x, y);
	}

	///// shadow /////

	public void drawShadowedString(Graphics g, String text, Font font, Color text_color, Color shadow_color,
			DIRECTION facing, int[] rect) {

		int gap = 4;
		int[] shadow_rect = { rect[0] + gap, rect[1] + gap, rect[2], rect[3] };

		new TextDecoration().drawCenteredString(g, text, font, shadow_color, facing, shadow_rect);
		new TextDecoration().drawCenteredString(g, text, font, text_color, facing, rect);
	}

	///// outlines /////

	public void drawOutlinesString(Graphics g, String text, Font font, DIRECTION facing, int[] rect) {

		drawOutlinesString(g, text, font, Color.WHITE, Color.BLACK, facing, rect);
	}

	public void drawOutlinesString(Graphics g, String text, Font font, Color text_color, Color outlines_color,
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

		new TextDecoration().drawCenteredString(g, text, font, outlines_color, facing, left_rect);
		new TextDecoration().drawCenteredString(g, text, font, outlines_color, facing, up_left_rect);
		new TextDecoration().drawCenteredString(g, text, font, outlines_color, facing, up_rect);
		new TextDecoration().drawCenteredString(g, text, font, outlines_color, facing, up_right_rect);
		new TextDecoration().drawCenteredString(g, text, font, outlines_color, facing, right_rect);
		new TextDecoration().drawCenteredString(g, text, font, outlines_color, facing, down_right_rect);
		new TextDecoration().drawCenteredString(g, text, font, outlines_color, facing, down_rect);
		new TextDecoration().drawCenteredString(g, text, font, outlines_color, facing, down_left_rect);

		new TextDecoration().drawCenteredString(g, text, font, text_color, facing, rect);
	}

}
