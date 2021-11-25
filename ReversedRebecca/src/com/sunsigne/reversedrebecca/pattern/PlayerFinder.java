package com.sunsigne.reversedrebecca.pattern;

import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player.Player;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;
import com.sunsigne.reversedrebecca.world.LAYER;

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
	
}