package com.sunsigne.reversedrebecca.physic.natural.correlated;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.camera.CameraDependency;
import com.sunsigne.reversedrebecca.system.camera.CameraOption;
import com.sunsigne.reversedrebecca.system.camera.CameraOption.CAMERA_TYPE;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class CameraMovingLaw implements PhysicLaw, CameraDependency {

	////////// TICK ////////////

	private boolean followingPlayer;

	public void setFollowingPlayer(boolean followingPlayer) {
		this.followingPlayer = followingPlayer;
	}

	@Override
	public void tick(Updatable object) {
		if (!followingPlayer)
			return;

		if (object instanceof Player == false)
			return;

		Player player = (Player) object;

		moveCameraByX(player);
		moveCameraByY(player);
	}

	private void moveCameraByX(Player player) {
		float targetX = -player.getX() + (Window.WIDHT - player.getWidth()) / 2;
		float cameraX = CAMERA.getX();

		float delay = 1.0f;
		if (CameraOption.getType() == CAMERA_TYPE.DYNAMIC)
			delay = 0.1f;

		CAMERA.setX(cameraX + (targetX - cameraX) * delay);
	}

	private void moveCameraByY(Player player) {
		float targetY = -player.getY() + (Window.HEIGHT - player.getHeight()) / 2;
		float cameraY = CAMERA.getY();

		float delay = 1.0f;
		if (CameraOption.getType() == CAMERA_TYPE.DYNAMIC)
			delay = 0.2f;

		CAMERA.setY(cameraY + (targetY - cameraY) * delay);
	}

	////////// RENDER ////////////

	@Override
	public void beforeObjectRender(Graphics g, Updatable object) {

	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {

	}

}
