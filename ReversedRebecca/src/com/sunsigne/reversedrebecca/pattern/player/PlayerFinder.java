package com.sunsigne.reversedrebecca.pattern.player;

import com.sunsigne.reversedrebecca.object.characteristics.Velocity;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.TilePos;
import com.sunsigne.reversedrebecca.physic.laws.independant.PlayerFinderLaw;
import com.sunsigne.reversedrebecca.system.Size;

public class PlayerFinder {

	////////// PLAYER ////////////

	public Player getPlayer() {
		return PlayerFinderLaw.getPlayer();
	}

	////////// DISTANCE ////////////

	private int getTilePos(int pos) {
		return new TilePos().getTilePos(pos, Size.M);
	}

	public boolean isPlayerFutherThan(Velocity object, int distanceInTiles) {
		int[] distance = getDistance(object, distanceInTiles);

		if (distance == null)
			return true;

		else
			return distance[0] > distance[1];
	}

	public boolean isPlayerCloserThan(Velocity object, int distanceInTiles) {
		int[] distance = getDistance(object, distanceInTiles);

		if (distance == null)
			return true;

		else
			return distance[0] < distance[1];
	}

	private int[] getDistance(Velocity object, int distanceInTiles) {
		Player player = getPlayer();

		if (player == null)
			return null;

		if (player.getHandler() != object.getHandler())
			return null;

		int diffX = getTilePos(object.getX()) - getTilePos(player.getX());
		int diffY = getTilePos(object.getY()) - getTilePos(player.getY());
		int playerDistance = (Math.abs(diffX) + Math.abs(diffY)) / Size.M;

		return new int[] { playerDistance, distanceInTiles };
	}

}