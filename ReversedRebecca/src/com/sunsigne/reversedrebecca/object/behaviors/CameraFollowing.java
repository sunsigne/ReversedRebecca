package com.sunsigne.reversedrebecca.object.behaviors;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.camera.CameraDependency;

public class CameraFollowing implements Behavior, CameraDependency {

	public CameraFollowing(ExtraBehaviorsObject object) {
		CameraFollowing.object = object;
	}

	////////// BEHAVIOR ////////////

	private static ExtraBehaviorsObject object;

	@Override
	public ExtraBehaviorsObject getExtraBehaviorsObject() {
		return object;
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
		CAMERA.setX(-object.getX() + (Window.WIDHT - object.getWidth()) / 2);
	}
	
	private void moveCameraByY() {
		CAMERA.setY(-object.getY() + (Window.HEIGHT - object.getHeight()) / 2);
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
