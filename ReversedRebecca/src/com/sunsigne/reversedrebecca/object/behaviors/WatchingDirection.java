package com.sunsigne.reversedrebecca.object.behaviors;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;

public class WatchingDirection implements Behavior {

	public WatchingDirection(ExtraBehaviorsObject object) {
		this.object = object;
	}

	////////// BEHAVIOR ////////////

	private ExtraBehaviorsObject object;

	@Override
	public ExtraBehaviorsObject getExtraBehaviorsObject() {
		return object;
	}

	////////// TICK ////////////

	private boolean flagX, flagY;
	
	@Override
	public void tick() {
		// if(!object.isPushed())
		updateWatchingDirection();
	}
	
	private void updateWatchingDirection() {
		if (object.isMotionlessbyX())
			flagX = false;
		if (object.isMotionlessbyY())
			flagY = false;

		if (!flagY && object.getVelX() < 0) {
			object.setFacing(DIRECTION.LEFT);
			flagX = true;
		}
		if (!flagY && object.getVelX() > 0) {
			object.setFacing(DIRECTION.RIGHT);
			flagX = true;
		}

		if (!flagX && object.getVelY() < 0) {
			object.setFacing(DIRECTION.UP);
			flagY = true;
		}
		if (!flagX && object.getVelY() > 0) {
			object.setFacing(DIRECTION.DOWN);
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
