package com.sunsigne.reversedrebecca.pattern.player;

import com.sunsigne.reversedrebecca.object.characteristics.Velocity;
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

	private int getTilePos(int pos) {
		return new TilePos().getTilePos(pos, Size.M);
	}

}