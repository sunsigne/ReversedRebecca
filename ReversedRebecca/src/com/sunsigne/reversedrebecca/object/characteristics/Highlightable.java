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

	////////// RENDER ////////////

	default void drawHighlight(Graphics g, BufferedImage image) {
		if (getHighlightCondition() == false)
			return;

		int pixel = getHighlightSize();
		g.drawImage(image, getX() - pixel, getY() - pixel, getWidth() + 2 * pixel,
				getHeight() + 2 * pixel, null);
	}

}
