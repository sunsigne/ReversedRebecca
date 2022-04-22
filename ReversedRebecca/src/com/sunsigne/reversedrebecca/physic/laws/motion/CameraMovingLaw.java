package com.sunsigne.reversedrebecca.physic.laws.motion;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.piranha.living.player.PiranhaPlayer;
import com.sunsigne.reversedrebecca.physic.laws.PhysicLaw;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.camera.CameraDependency;
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

		if (object == null)
			return;

		if (object instanceof PiranhaPlayer == false)
			return;

		PiranhaPlayer player = (PiranhaPlayer) object;

		moveCameraByX(player);
		moveCameraByY(player);
	}

	private void moveCameraByX(PiranhaPlayer player) {
		CAMERA.setX(-player.getX() + (Window.WIDHT - player.getWidth()) / 2);
	}

	private void moveCameraByY(PiranhaPlayer player) {
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
