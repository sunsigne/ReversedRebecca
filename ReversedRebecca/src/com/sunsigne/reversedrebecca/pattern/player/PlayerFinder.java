package com.sunsigne.reversedrebecca.pattern.player;

import com.sunsigne.reversedrebecca.object.characteristics.Velocity;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.players.Player;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
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

	public boolean isPlayerFutherThan(Velocity object, int distance) {
		Player player = getPlayer();

		if (player == null)
			return true;

		if (player.getHandler() != object.getHandler())
			return true;

		int diffX = object.getX() - player.getX();
		int diffY = object.getY() - player.getY();
		float playerDistance = (float) Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2));

		return playerDistance > distance;
	}

}