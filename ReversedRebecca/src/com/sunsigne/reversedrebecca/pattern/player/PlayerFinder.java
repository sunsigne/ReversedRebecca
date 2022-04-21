package com.sunsigne.reversedrebecca.pattern.player;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.Velocity;
import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.Behavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.players.Player;
import com.sunsigne.reversedrebecca.object.piranha.player.PiranhaPlayer;
import com.sunsigne.reversedrebecca.pattern.TilePos;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class PlayerFinder {

	////////// PLAYER ////////////
	
	public Player getPlayer() {

		for (LAYER tempLayer : LAYER.values()) {
			var list = tempLayer.getHandler().getList();

			for (Updatable tempUpdatable : list) {
				if (tempUpdatable instanceof Player)
					return (Player) tempUpdatable;
			}
		}
		return null;
	}
	
	public PiranhaPlayer getPiranhaPlayer() {

		for (LAYER tempLayer : LAYER.values()) {
			var list = tempLayer.getHandler().getList();

			for (Updatable tempUpdatable : list) {
				if (tempUpdatable instanceof PiranhaPlayer)
					return (PiranhaPlayer) tempUpdatable;
			}
		}
		return null;
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
		PiranhaPlayer player = getPiranhaPlayer();

		if (player == null)
			return null;

		if (player.getHandler() != object.getHandler())
			return null;

		int diffX = getTilePos(object.getX()) - getTilePos(player.getX());
		int diffY = getTilePos(object.getY()) - getTilePos(player.getY());
		int playerDistance = (Math.abs(diffX) + Math.abs(diffY)) / Size.M;

		return new int[] { playerDistance, distanceInTiles };
	}

	////////// BEHAVIOR ////////////
	
	public boolean isPlayerInvolved(CollisionDetector detectorObject) {
		if (detectorObject instanceof Player)
			return true;

		if (detectorObject instanceof Behavior) {
			Behavior behavior = (Behavior) detectorObject;
			if (behavior.getExtraBehaviorsObject() instanceof Player)
				return true;
		}
		return false;
	}

}