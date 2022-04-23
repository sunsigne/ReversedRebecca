package com.sunsigne.reversedrebecca.physic.laws.motion;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.physic.laws.PhysicLaw;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.camera.CameraDependency;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class CameraMovingLaw implements PhysicLaw, CameraDependency {

	////////// TICK ////////////

	private boolean followingPlayer = true;

	public void setFollowingPlayer(boolean followingPlayer) {
		this.followingPlayer = followingPlayer;
	}

	@Override
	public void tick(Updatable object) {
		if (!followingPlayer)
			return;

		if (object == null)
			return;

		if (object instanceof Player == false)
			return;

		Player player = (Player) object;

		moveCameraByX(player);
		moveCameraByY(player);
	}

	private void moveCameraByX(Player player) {
		CAMERA.setX(-player.getX() + (Window.WIDHT - player.getWidth()) / 2);
	}

	private void moveCameraByY(Player player) {
		CAMERA.setY(-player.getY() + (Window.HEIGHT - player.getHeight()) / 2);
	}

	////////// RENDER ////////////

	@Override
	public void beforeObjectRender(Graphics g, Updatable object) {

	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {

	}

}
