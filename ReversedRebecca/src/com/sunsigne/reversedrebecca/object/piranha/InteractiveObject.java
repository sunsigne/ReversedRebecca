package com.sunsigne.reversedrebecca.object.piranha;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;
import com.sunsigne.reversedrebecca.world.World;

public class InteractiveObject extends PiranhaObject implements TickFree {

	public InteractiveObject(String name, int x, int y) {
		super(name, x, y);
		setStunned(true); // looks stupid, but allow to bypass the RoundToTileLaw

		refreshHighlight();
	}

	////////// PATH FINDER ////////////

	@Override
	public boolean mustFollowPath() {
		return false;
	}

	////////// COLLISION ////////////

	@Override
	public void collidingReaction(CollisionDetector detectorObject) {
		blockPath(detectorObject);
	}

	////////// HIGHLIGHT ////////////

	private BufferedImage highlightImage;

	@Override
	public boolean getHighlightCondition() {
		if (highlightImage == null)
			return false;

		return super.getHighlightCondition();
	}

	public void refreshHighlight() {
		highlightImage = new ImageTask().loadImage("maps/" + World.get().getMapName() + "/" + "highlights/" + getName(),
				true);
	}

	////////// RENDER ////////////

	private int[] highlight_rect = new int[4];

	public void setHighlightRect(int x, int y, int width, int height) {
		int ratio = Size.M;
		this.highlight_rect[0] = x * ratio;
		this.highlight_rect[1] = y * ratio;
		this.highlight_rect[2] = (width - 1) * ratio;
		this.highlight_rect[3] = (height - 1) * ratio;
	}

	@Override
	public void render(Graphics g) {
		drawHighlight(g, highlightImage, highlight_rect[0], highlight_rect[1], highlight_rect[2], highlight_rect[3]);
	}

}
