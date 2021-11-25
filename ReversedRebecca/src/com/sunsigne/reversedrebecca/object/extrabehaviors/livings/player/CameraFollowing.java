package com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.object.extrabehaviors.Behavior;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.camera.CameraDependency;

public class CameraFollowing implements Behavior, CameraDependency {

	public CameraFollowing(Player player) {
		CameraFollowing.player = player;
	}

	////////// BEHAVIOR ////////////

	private static Player player;

	@Override
	public Player getExtraBehaviorsObject() {
		return player;
	}
	
	////////// CAMERA ////////////

	@Override
	public boolean isCameraDependant() {
		return false;
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		moveCameraByX();
		moveCameraByY();
	}

	private void moveCameraByX() {
		CAMERA.setX(-player.getX() + (Window.WIDHT - player.getWidth()) / 2);
	}
	
	private void moveCameraByY() {
		CAMERA.setY(-player.getY() + (Window.HEIGHT - player.getHeight()) / 2);
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
