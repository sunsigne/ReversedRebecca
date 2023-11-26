package com.sunsigne.reversedrebecca.object.characteristics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.system.Size;

public interface Highlightable extends Velocity {

	////////// HIGHLIGHT ////////////

	boolean getHighlightCondition();

	default int getHighlightSize() {
		return (2 * getWidth()) / Size.XS;
	}

	default boolean isHighlightAbovePlayer() {
		return true;
	}
	
	////////// RENDER ////////////

	default void drawHighlight(Graphics g, BufferedImage image) {
		drawHighlight(g, image, 0, 0, 0, 0);
	}

	default void drawHighlight(Graphics g, BufferedImage image, int x, int y, int width, int height) {
		if (getHighlightCondition() == false)
			return;

		int pixel = getHighlightSize();
		g.drawImage(image, x + getX() - pixel, y + getY() - pixel, width + getWidth() + 2 * pixel,
				height + getHeight() + 2 * pixel, null);
	}

}
