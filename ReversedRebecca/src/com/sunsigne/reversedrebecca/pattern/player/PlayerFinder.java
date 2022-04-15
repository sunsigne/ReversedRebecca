package com.sunsigne.reversedrebecca.pattern.player;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.Velocity;
import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.Behavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.players.Player;
import com.sunsigne.reversedrebecca.pattern.TilePos;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class PlayerFinder {

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

	public boolean isPlayerFutherThan(Velocity object, int distanceInTiles) {
		Player player = getPlayer();

		if (player == null)
			return true;

		if (player.getHandler() != object.getHandler())
			return true;

		int diffX = getTilePos(object.getX()) - getTilePos(player.getX());
		int diffY = getTilePos(object.getY()) - getTilePos(player.getY());
		int playerDistance = (Math.abs(diffX) + Math.abs(diffY)) / Size.M;

		return playerDistance > distanceInTiles;
	}
	
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

	private int getTilePos(int pos) {
		return new TilePos().getTilePos(pos, Size.M);
	}

}