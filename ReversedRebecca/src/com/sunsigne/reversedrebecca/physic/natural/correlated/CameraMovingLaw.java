package com.sunsigne.reversedrebecca.physic.natural.correlated;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.GameTimer;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.camera.CameraDependency;
import com.sunsigne.reversedrebecca.system.camera.CameraOption;
import com.sunsigne.reversedrebecca.system.camera.CameraOption.CAMERA_TYPE;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;
import com.sunsigne.reversedrebecca.world.World;

public class CameraMovingLaw implements PhysicLaw, CameraDependency {

	////////// TICK ////////////

	private boolean followingPlayer;
	private boolean tempDynamic;

	public void setFollowingPlayer(boolean followingPlayer) {
		this.followingPlayer = followingPlayer;
	}

	public void setFollowingPlayer(boolean followingPlayer, boolean tempDynamic) {
		this.followingPlayer = followingPlayer;

		if (tempDynamic) {
			this.tempDynamic = true;
			new GameTimer(30, () -> this.tempDynamic = false);
		}
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

		targetX = getBorderedTarget(targetX, true);

		if (CameraOption.getType() == CAMERA_TYPE.STATIC && tempDynamic == false) {
			CAMERA.setX(targetX); // == CAMERA.setX(cameraX + (targetX - cameraX) * delay);
			return;
		}

		delay = 0.15f;
		if (isPlayerFutherThan(CAMERA.getX(), targetX, 0.3f))
			CAMERA.setX(cameraX + (targetX - cameraX) * delay);
	}

	private void moveCameraByY(Player player) {
		float targetY = -player.getY() + (Window.HEIGHT - player.getHeight()) / 2;
		float cameraY = CAMERA.getY();
		float delay = 1.0f;

		targetY = getBorderedTarget(targetY, false);

		if (CameraOption.getType() == CAMERA_TYPE.STATIC && tempDynamic == false) {
			CAMERA.setY(targetY); // == CAMERA.setY(cameraY + (targetY - cameraY) * delay);
			return;
		}

		delay = 0.2f;
		if (isPlayerFutherThan(CAMERA.getY(), targetY, 0.3f))
			CAMERA.setY(cameraY + (targetY - cameraY) * delay);
	}

	// prevent camera to protrude from the borders' map
	private float getBorderedTarget(float target, boolean horizontal) {

		// border left or up
		if (target >= 0)
			return 0;

		// if no world found
		World world = World.get();
		if (world == null)
			return target;

		// border right or down
		int maxBorder = horizontal ? Window.WIDHT - world.getWidth() : Window.HEIGHT - world.getHeight();
		if (target <= maxBorder)
			return maxBorder;

		return target;
	}

	public boolean isPlayerFutherThan(float cameraPos, float target, float distanceInTiles) {
		Player player = new PlayerFinder().getPlayer();

		if (player == null)
			return true;

		float diffX = cameraPos - target;
		float playerDistance = (Math.abs(diffX)) / player.getSize();
		
		if(tempDynamic)
			distanceInTiles = 0;

		return playerDistance > distanceInTiles;
	}

	////////// RENDER ////////////

	@Override
	public void beforeObjectRender(Graphics g, Updatable object) {

	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {

	}

}
