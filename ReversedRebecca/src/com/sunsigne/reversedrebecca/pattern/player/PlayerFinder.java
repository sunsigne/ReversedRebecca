package com.sunsigne.reversedrebecca.pattern.player;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player.Player;
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

	public boolean isPlayerFutherThan(GameObject object, int distance) {		
		int diffX = object.getX() - getPlayer().getX();
		int diffY = object.getY() - getPlayer().getY();
		float playerDistance = (float) Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2));

		return playerDistance > distance;
	}
}