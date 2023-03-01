package com.sunsigne.reversedrebecca.object.piranha.living.player;

import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.DownKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.LeftKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.RightKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.UpKey;

public class UserKeyMovePlayer {

	////////// SIGNELTON ////////////

	private UserKeyMovePlayer() {
		refreshDirectionKeys();
	}

	private static UserKeyMovePlayer instance;

	public static UserKeyMovePlayer getInstance() {
		if (instance == null)
			instance = new UserKeyMovePlayer();
		return instance;
	}

	protected static void refreshInstance() {
		instance = null;
	}

	////////// TICK ////////////

	public void movePlayerByKey(Player player) {
		movePlayerbyX(player);
		movePlayerbyY(player);
	}

	private void movePlayerbyX(Player player) {

		if (directionKeyPressed[DIRECTION.LEFT.getNum()] && !directionKeyPressed[DIRECTION.RIGHT.getNum()])
			player.setVelX(-player.getSpeed());
		else if (directionKeyPressed[DIRECTION.LEFT.getNum()] && directionKeyPressed[DIRECTION.RIGHT.getNum()])
			player.setVelX(0);
		else if (!directionKeyPressed[DIRECTION.LEFT.getNum()] && directionKeyPressed[DIRECTION.RIGHT.getNum()])
			player.setVelX(player.getSpeed());
		else if (!directionKeyPressed[DIRECTION.LEFT.getNum()] && !directionKeyPressed[DIRECTION.RIGHT.getNum()])
			player.setVelX(0);
	}

	private void movePlayerbyY(Player player) {

		if (directionKeyPressed[DIRECTION.UP.getNum()] && !directionKeyPressed[DIRECTION.DOWN.getNum()])
			player.setVelY(-player.getSpeed());
		else if (directionKeyPressed[DIRECTION.UP.getNum()] && directionKeyPressed[DIRECTION.DOWN.getNum()])
			player.setVelY(0);
		else if (!directionKeyPressed[DIRECTION.UP.getNum()] && directionKeyPressed[DIRECTION.DOWN.getNum()])
			player.setVelY(player.getSpeed());
		else if (!directionKeyPressed[DIRECTION.UP.getNum()] && !directionKeyPressed[DIRECTION.DOWN.getNum()])
			player.setVelY(0);
	}

	////////// KEYBOARD ////////////

	private int[] directionKeyEvent = new int[4];
	private boolean[] directionKeyPressed = new boolean[4];

	public void refreshDirectionKeys() {
		setDirectionKeyEvent(DIRECTION.LEFT, LeftKey.getKey());
		setDirectionKeyEvent(DIRECTION.RIGHT, RightKey.getKey());
		setDirectionKeyEvent(DIRECTION.UP, UpKey.getKey());
		setDirectionKeyEvent(DIRECTION.DOWN, DownKey.getKey());
	}

	private void setDirectionKeyEvent(DIRECTION direction, int directionKeyEvent) {
		this.directionKeyEvent[direction.getNum()] = directionKeyEvent;
	}

	public void directionKey(Player player, int key, int button, boolean pressed) {
		if (player == null)
			return;

		if (key == directionKeyEvent[DIRECTION.LEFT.getNum()] || key == KeyEvent.VK_LEFT || button == ButtonEvent.LEFT)
			directionKeyPressed[DIRECTION.LEFT.getNum()] = pressed;

		if (key == directionKeyEvent[DIRECTION.RIGHT.getNum()] || key == KeyEvent.VK_RIGHT || button == ButtonEvent.RIGHT)
			directionKeyPressed[DIRECTION.RIGHT.getNum()] = pressed;

		if (key == directionKeyEvent[DIRECTION.UP.getNum()] || key == KeyEvent.VK_UP || button == ButtonEvent.UP)
			directionKeyPressed[DIRECTION.UP.getNum()] = pressed;

		if (key == directionKeyEvent[DIRECTION.DOWN.getNum()] || key == KeyEvent.VK_DOWN || button == ButtonEvent.DOWN)
			directionKeyPressed[DIRECTION.DOWN.getNum()] = pressed;

		if (button == ButtonEvent.NULL_X) {
			directionKeyPressed[DIRECTION.LEFT.getNum()] = false;
			directionKeyPressed[DIRECTION.RIGHT.getNum()] = false;
		}

		if (button == ButtonEvent.NULL_Y) {
			directionKeyPressed[DIRECTION.UP.getNum()] = false;
			directionKeyPressed[DIRECTION.DOWN.getNum()] = false;
		}

	}

}
