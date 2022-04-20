package com.sunsigne.reversedrebecca.object.piranha;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.UserKeyMovePlayer;

public class PiranhaPlayer extends PiranhaObject {

	public PiranhaPlayer(int x, int y) {
		super("PLAYER", x, y);
		setDisabled(true);
		userCanKeyMove = true;
	}

	////////// SPEED ////////////

	private void updateSpeed() {
		if (getPath() != DIRECTION.NULL)
			return;

		setSpeedness(SPEEDNESS.PLAYER_SPEED);
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		updateSpeed();

		if (canUserKeyMove())
			UserKeyMovePlayer.getInstance().movePlayerByKey(this);
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {

	}

	////////// KEYBOARD ////////////

	private boolean userCanKeyMove;

	private boolean canUserKeyMove() {
		if (getPath() != null & getPath() != DIRECTION.NULL)
			return false;

		else
			return userCanKeyMove;
	}

	public void setUserCanKeyMove(boolean userCanKeyMove) {
		this.userCanKeyMove = userCanKeyMove;
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
	}

	@Override
	public void collidingReaction(CollisionDetector detectorObject) {

	}

}
