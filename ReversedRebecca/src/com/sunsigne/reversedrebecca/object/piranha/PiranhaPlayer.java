package com.sunsigne.reversedrebecca.object.piranha;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;

public class PiranhaPlayer extends PiranhaObject {

	public PiranhaPlayer(int x, int y) {
		super("PLAYER", x, y);
		setDisabled(true);
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		updateSpeed();
	}

	private void updateSpeed() {
		if (getPath() == null)
			return;

		if (getPath() == DIRECTION.NULL)
			return;

		setSpeedness(SPEEDNESS.PLAYER_SPEED);
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {

	}

	////////// COLLISION ////////////

	@Override
	public boolean isBlockingPath() {
		return false;
	}

	@Override
	public void collidingReaction(CollisionDetector detectorObject) {
		blockPath(detectorObject);
	}

}
