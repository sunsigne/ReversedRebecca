package com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
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
	
	////////// POSITION ////////////
	
	@Override
	public int getX() {
		return getExtraBehaviorsObject().getX();
	}
	
	@Override
	public int getY() {
		return getExtraBehaviorsObject().getY();
	}
	
	@Override
	public void setX(int x) {
		getExtraBehaviorsObject().setX(x);
	}
	
	@Override
	public void setY(int y) {
		getExtraBehaviorsObject().setY(y);
	}
	
	////////// SIZE ////////////
	
	@Override
	public int getWidth() {
		return getExtraBehaviorsObject().getWidth();
	}
	
	@Override
	public int getHeight() {
		return getExtraBehaviorsObject().getHeight();
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

	//////////COLLISION ////////////
	
	@Override
	public void collidingReaction(CollisionDetector detectorObject) {

	}

}
