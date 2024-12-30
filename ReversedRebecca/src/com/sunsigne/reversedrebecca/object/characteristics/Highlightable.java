package com.sunsigne.reversedrebecca.object.characteristics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.interactive.Interactive;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
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

	default boolean adaptSpreadInteractivenessHighlight() {
		return true;
	}

	////////// RENDER ////////////

	default void drawHighlight(Graphics g, BufferedImage image) {
		drawHighlight(g, image, 0, 0, 0, 0);
	}

	default void drawHighlight(Graphics g, BufferedImage image, int x, int y, int width, int height) {
		if (getHighlightCondition() == false)
			return;

		int[] pos = getPos();

		int pixel = getHighlightSize();
		g.drawImage(image, x + pos[0] - pixel, y + pos[1] - pixel, width + getWidth() + 2 * pixel,
				height + getHeight() + 2 * pixel, null);
	}

	default int[] getPos() {
		int[] pos = new int[] { getX(), getY() };

		if (this instanceof Interactive == false)
			return pos;

		Interactive interactive = (Interactive) this;
		var list = interactive.getSpreadInteractivenessList().getList();

		if (list.size() == 1)
			return pos;

		PlayerFinder finder = new PlayerFinder();
		Player player = finder.getPlayer();
		if (player == null)
			return pos;

		Highlightable closestInteractive = interactive;
		int distance = finder.getDistance(closestInteractive, -1)[0];

		for (Highlightable tempInteractive : list) {
			if (tempInteractive.adaptSpreadInteractivenessHighlight() == false)
				continue;

			int tempDistance = finder.getDistance(tempInteractive, -1)[0];
			if (tempDistance < distance)
				closestInteractive = tempInteractive;
		}

		pos = new int[] { closestInteractive.getX(), closestInteractive.getY() };
		return pos;
	}

}
