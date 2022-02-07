package com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player.behaviors;

import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.TickBehavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player.Player;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.camera.CameraDependency;

public class CameraFollowing implements TickBehavior, CameraDependency {

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
		int velX = player.isSurMotionless() ? player.getVelX() : player.getSurVelX();
		CAMERA.setX(-player.getX() - velX + (Window.WIDHT - player.getWidth()) / 2);
	}
	
	private void moveCameraByY() {
		int velY = player.isSurMotionless() ? player.getVelY() : player.getSurVelY();
		CAMERA.setY(-player.getY() - velY + (Window.HEIGHT - player.getHeight()) / 2);
	}
	
}
