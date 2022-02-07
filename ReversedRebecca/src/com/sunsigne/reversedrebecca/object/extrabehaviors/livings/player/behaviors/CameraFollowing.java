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
		CAMERA.setX(-player.getX() - player.getVelX() - player.getSurVelX() + (Window.WIDHT - player.getWidth()) / 2);
	}
	
	private void moveCameraByY() {
		CAMERA.setY(-player.getY() - player.getVelY() - player.getSurVelY() + (Window.HEIGHT - player.getHeight()) / 2);
	}
	
}
