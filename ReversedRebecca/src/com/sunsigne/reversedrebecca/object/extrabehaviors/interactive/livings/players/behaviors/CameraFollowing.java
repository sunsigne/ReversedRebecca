package com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.players.behaviors;

import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.TickBehavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.players.Player;
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
		CAMERA.setX(-player.getX() + (Window.WIDHT - player.getWidth()) / 2);
	}

	private void moveCameraByY() {
		CAMERA.setY(-player.getY() + (Window.HEIGHT - player.getHeight()) / 2);
	}

}