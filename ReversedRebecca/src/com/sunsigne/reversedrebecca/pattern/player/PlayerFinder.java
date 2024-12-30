package com.sunsigne.reversedrebecca.pattern.player;

import com.sunsigne.reversedrebecca.menu.Cutscene;
import com.sunsigne.reversedrebecca.object.characteristics.Velocity;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.TilePos;
import com.sunsigne.reversedrebecca.physic.natural.independant.PlayerFinderLaw;

public class PlayerFinder {

	////////// PLAYER ////////////

	public Player getPlayer() {
		return PlayerFinderLaw.getPlayer();
	}

	public void roundToTilePlayer() {
		Player player = getPlayer();

		if (player == null)
			return;
		
		int x = getTilePos(player.getX());
		int y = getTilePos(player.getY());
		player.setX(x);
		player.setY(y);
	}
	
	////////// CONTROL ////////////

	public void setPlayerCanInteract(boolean canInteract) {
		Player player = getPlayer();

		if (player == null || Cutscene.isRunning())
			return;

		player.setCanInteract(canInteract);
	}

	public void setUserAllowedToControlPlayer(boolean isUserAllowedToMovePlayer) {
		Player player = getPlayer();

		if (player == null)
			return;

		player.setUserAllowedToMovePlayer(isUserAllowedToMovePlayer);
		player.setCanInteract(isUserAllowedToMovePlayer);
	}

	////////// DISTANCE ////////////

	public boolean isPlayerFutherThan(Velocity object, int distanceInTiles) {
		int[] distance = getDistance(object, distanceInTiles);

		if (distance == null)
			return true;

		return distance[0] > distance[1];
	}

	public boolean isPlayerCloserThan(Velocity object, int distanceInTiles) {
		int[] distance = getDistance(object, distanceInTiles);

		if (distance == null)
			return false;

		return distance[0] < distance[1];
	}

	private int getTilePos(int pos) {
		return new TilePos().getTilePos(pos, getPlayer().getSize());
	}

	public int[] getDistance(Velocity object, int distanceInTiles) {
		Player player = getPlayer();

		if (player == null)
			return null;

		if (player.getHandler() != object.getHandler())
			return null;

		int diffX = getTilePos(object.getX()) - getTilePos(player.getX());
		int diffY = getTilePos(object.getY()) - getTilePos(player.getY());
		int playerDistance = (Math.abs(diffX) + Math.abs(diffY)) / player.getSize();

		return new int[] { playerDistance, distanceInTiles };
	}

}