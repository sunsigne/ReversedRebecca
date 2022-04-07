package com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.players.behaviors;

import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.KeyboardBehavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.TickBehavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.players.Player;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardController;

public class UserCanKeyMove implements TickBehavior, KeyboardBehavior {

	public UserCanKeyMove(Player player) {
		this.player = player;
		initDirectionKeys();
	}

	////////// BEHAVIOR ////////////
	
	private Player player;
	
	@Override
	public Player getExtraBehaviorsObject() {
		return player;
	}
	
	////////// TICK ////////////

	@Override
	public void tick() {
		movePlayerbyX();
		movePlayerbyY();
	}

	private void movePlayerbyX() {

		if (directionKeyPressed[DIRECTION.LEFT.getNum()] && !directionKeyPressed[DIRECTION.RIGHT.getNum()])
			player.setVelX(-player.speed);
		else if (directionKeyPressed[DIRECTION.LEFT.getNum()] && directionKeyPressed[DIRECTION.RIGHT.getNum()])
			player.setVelX(0);
		else if (!directionKeyPressed[DIRECTION.LEFT.getNum()] && directionKeyPressed[DIRECTION.RIGHT.getNum()])
			player.setVelX(player.speed);
		else if (!directionKeyPressed[DIRECTION.LEFT.getNum()] && !directionKeyPressed[DIRECTION.RIGHT.getNum()])
			player.setVelX(0);
	}

	private void movePlayerbyY() {

		if (directionKeyPressed[DIRECTION.UP.getNum()] && !directionKeyPressed[DIRECTION.DOWN.getNum()])
			player.setVelY(-player.speed);
		else if (directionKeyPressed[DIRECTION.UP.getNum()] && directionKeyPressed[DIRECTION.DOWN.getNum()])
			player.setVelY(0);
		else if (!directionKeyPressed[DIRECTION.UP.getNum()] && directionKeyPressed[DIRECTION.DOWN.getNum()])
			player.setVelY(player.speed);
		else if (!directionKeyPressed[DIRECTION.UP.getNum()] && !directionKeyPressed[DIRECTION.DOWN.getNum()])
			player.setVelY(0);
	}

	////////// KEYBOARD ////////////
	
	private int[] directionKeyEvent = new int[4];
	private boolean[] directionKeyPressed = new boolean[4];
	
	@Override
	public KeyboardController getKeyBoardController() {
		return player.getKeyBoardController();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		directionKey(key, true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		directionKey(key, false);
	}	

	private void initDirectionKeys() {
		setDirectionKeyEvent(DIRECTION.LEFT, KeyEvent.VK_Q);
		setDirectionKeyEvent(DIRECTION.RIGHT, KeyEvent.VK_D);
		setDirectionKeyEvent(DIRECTION.UP, KeyEvent.VK_Z);
		setDirectionKeyEvent(DIRECTION.DOWN, KeyEvent.VK_S);
	}

	private void setDirectionKeyEvent(DIRECTION direction, int directionKeyEvent) {
		this.directionKeyEvent[direction.getNum()] = directionKeyEvent;
	}

	private void directionKey(int key, boolean pressed) {

		if (key == directionKeyEvent[DIRECTION.LEFT.getNum()] || key == KeyEvent.VK_LEFT)
			directionKeyPressed[DIRECTION.LEFT.getNum()] = pressed;

		if (key == directionKeyEvent[DIRECTION.RIGHT.getNum()] || key == KeyEvent.VK_RIGHT)
			directionKeyPressed[DIRECTION.RIGHT.getNum()] = pressed;

		if (key == directionKeyEvent[DIRECTION.UP.getNum()] || key == KeyEvent.VK_UP)
			directionKeyPressed[DIRECTION.UP.getNum()] = pressed;

		if (key == directionKeyEvent[DIRECTION.DOWN.getNum()] || key == KeyEvent.VK_DOWN)
			directionKeyPressed[DIRECTION.DOWN.getNum()] = pressed;
	}

}
