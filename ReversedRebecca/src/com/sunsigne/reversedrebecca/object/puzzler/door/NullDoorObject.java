package com.sunsigne.reversedrebecca.object.puzzler.door;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.sunsigne.reversedrebecca.object.Wall.COLOR;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.pattern.cycloid.Cycloid;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.system.Size;

public class NullDoorObject extends DoorObject {

	public NullDoorObject(COLOR color, int x, int y) {
		super(LVL.NULL, color, x, y);
		setDisabled(true);
		tryClosing = new Cycloid<>(false, false, true);
	}

	////////// TICK ////////////

	private boolean isOpened;
	private Cycloid<Boolean> tryClosing;

	@Override
	public void tick() {
		if (tryClosing.getState()) {
			if (isOpened)
				if (LAYER.MENU.getHandler().getList().isEmpty())
					new SoundTask().playSoundIfCamera(this, "door_close");
			isOpened = false;
		}

		tryClosing.cycle();
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		if (isOpened)
			return;

		super.render(g);
	}

	////////// COLLISION ////////////

	@Override
	public boolean isBlockingSight() {
		return isOpened == false;
	}

	@Override
	public boolean isBlockingPath() {
		return false;
	}

	@Override
	public void collidingReaction(CollisionDetector detectorObject) {
		collidingReaction(detectorObject, false, () -> {
			if (!isOpened)
				if (LAYER.MENU.getHandler().getList().isEmpty())
					new SoundTask().playSoundIfCamera(this, "door_open");
			isOpened = true;
			tryClosing.setState(0);
		});
	}

	@Override
	public Rectangle getBounds() {
		int pixel = 8 * Size.M / 96;
		int x = getX() + 1 * pixel;
		int y = getY() + 1 * pixel;
		int w = getWidth() - 2 * pixel;
		int h = getHeight() - 2 * pixel;
		return new Rectangle(x, y, w, h);
	}
	
}
