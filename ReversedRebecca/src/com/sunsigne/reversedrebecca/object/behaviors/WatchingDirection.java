package com.sunsigne.reversedrebecca.object.behaviors;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;

public class WatchingDirection implements Behavior {

	public WatchingDirection(ExtraBehaviorsObject object) {
		this.object = object;
		watching[DIRECTION.DOWN.getNum()] = true;
		facing = DIRECTION.DOWN;
	}

	////////// BEHAVIOR ////////////

	private ExtraBehaviorsObject object;

	@Override
	public ExtraBehaviorsObject getExtraBehaviorsObject() {
		return object;
	}

	////////// FACING ////////////

	private DIRECTION facing;
	protected boolean[] watching = new boolean[4];
	private boolean flagX, flagY;

	public DIRECTION getFacing() {
		return facing;
	}

	public void setFacing(DIRECTION facing) {
		resetWatchingDirection();
		watching[facing.getNum()] = true;
		this.facing = facing;
	}

	private void resetWatchingDirection() {
		watching[DIRECTION.LEFT.getNum()] = false;
		watching[DIRECTION.RIGHT.getNum()] = false;
		watching[DIRECTION.UP.getNum()] = false;
		watching[DIRECTION.DOWN.getNum()] = false;
	}

	////////// TICK ////////////

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
			resetWatchingDirection();
			watching[DIRECTION.LEFT.getNum()] = true;
			facing = DIRECTION.LEFT;
			flagX = true;
		}
		if (!flagY && object.getVelX() > 0) {
			resetWatchingDirection();
			watching[DIRECTION.RIGHT.getNum()] = true;
			facing = DIRECTION.RIGHT;
			flagX = true;
		}

		if (!flagX && object.getVelY() < 0) {
			resetWatchingDirection();
			watching[DIRECTION.UP.getNum()] = true;
			facing = DIRECTION.UP;
			flagY = true;
		}
		if (!flagX && object.getVelY() > 0) {
			resetWatchingDirection();
			watching[DIRECTION.DOWN.getNum()] = true;
			facing = DIRECTION.DOWN;
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
		int key = e.getKeyCode();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
	}

}
