package com.sunsigne.reversedrebecca.pattern.player;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player.Player;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player.behaviors.PlayerHealth;
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
	
	public PlayerHealth getPlayerHealth() {
		if (getPlayer() == null)
			return null;

		PlayerHealth playerHealth = new PlayerHealth(getPlayer());

		if (!getPlayer().getBehaviorList().cointainsObject(playerHealth))
			return null;

		return (PlayerHealth) getPlayer().getBehaviorList().getObject(playerHealth);
	}
	
	public boolean isPlayerFutherThan(GameObject object, int distance) {
		int diffX = object.getX() - getPlayer().getX();
		int diffY = object.getY() - getPlayer().getY();
		float playerDistance = (float) Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2));

		return playerDistance > distance;
	}

}