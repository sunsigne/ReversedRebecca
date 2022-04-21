package com.sunsigne.reversedrebecca.object.piranha.living.player;

import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.piranha.living.LivingObject;

public class PiranhaPlayer extends LivingObject {

	public PiranhaPlayer(int x, int y) {
		super("PLAYER", x, y);
		setDisabled(true);
		isUserAllowedToMovePlayer = true;
		canInterract = true;
	}

	////////// SPEED ////////////

	private void updateSpeed() {
		if (isPathNull())
			setSpeedness(SPEEDNESS.PLAYER_SPEED);
	}

	////////// PATH FINDER ////////////

	private boolean isPathNull() {
		if (getPath() == null)
			return true;
		if (getPath() == DIRECTION.NULL)
			return true;
		else
			return false;
	}

	@Override
	public boolean mustFollowPath() {
		return isPathNull();
	}
	
	////////// INTERACTIVE ////////////

	private boolean canInterract;

	public boolean canInterract() {
		return canInterract;
	}

	public void setCanInterract(boolean canInterract) {
		this.canInterract = canInterract;
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		if (isUserAllowedToMovePlayer())
			UserKeyMovePlayer.getInstance().movePlayerByKey(this);

		super.tick();

		updateSpeed();
	}

	////////// KEYBOARD ////////////

	private boolean isUserAllowedToMovePlayer;

	private boolean isUserAllowedToMovePlayer() {
		if(!isPathNull())
			return false;

		else
			return isUserAllowedToMovePlayer;
	}

	public void setUserAllowedToMovePlayer(boolean isUserAllowedToMovePlayer) {
		setMotionless();
		this.isUserAllowedToMovePlayer = isUserAllowedToMovePlayer;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		UserKeyMovePlayer.getInstance().directionKey(this, key, true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		UserKeyMovePlayer.getInstance().directionKey(this, key, false);
	}

	////////// COLLISION ////////////

	@Override
	public boolean isBlockingPath() {
		return false;
		// it depends on context ! But "true" corrupts PathFinding.
	}

	@Override
	public void collidingReaction(CollisionDetector detectorObject) {

	}

}
