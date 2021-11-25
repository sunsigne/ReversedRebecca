package com.sunsigne.reversedrebecca.object.behaviors;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.object.characteristics.Facing;

public class WatchingDirection implements Behavior, Facing {

	public WatchingDirection(ExtraBehaviorsObject object) {
		this.object = object;
	}

	////////// BEHAVIOR ////////////

	private ExtraBehaviorsObject object;

	@Override
	public ExtraBehaviorsObject getExtraBehaviorsObject() {
		return object;
	}

	////////// FACING ////////////

	private boolean flagX, flagY;

	@Override
	public DIRECTION getFacing() {
		return object.getFacing();
	}

	@Override
	public void setFacing(DIRECTION facing) {
		object.setFacing(facing);
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		updateWatchingDirection();
	}

	private void updateWatchingDirection() {
		if (object.isMotionlessbyX())
			flagX = false;
		if (object.isMotionlessbyY())
			flagY = false;

		if (!flagY && object.getVelX() < 0) {
			setFacing(DIRECTION.LEFT);
			flagX = true;
		}
		if (!flagY && object.getVelX() > 0) {
			setFacing(DIRECTION.RIGHT);
			flagX = true;
		}

		if (!flagX && object.getVelY() < 0) {
			setFacing(DIRECTION.UP);
			flagY = true;
		}
		if (!flagX && object.getVelY() > 0) {
			setFacing(DIRECTION.DOWN);
			flagY = true;
		}
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {

	}

	////////// KEYBOARD ////////////

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}
