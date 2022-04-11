package com.sunsigne.reversedrebecca.system.controllers.keyboard.keys;

import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.players.Player;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.players.behaviors.UserCanKeyMove;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;

public interface DirectionalKey extends Key {

	default void updateKeyMoveKeys() {
		Player player = new PlayerFinder().getPlayer();
		if (player == null)
			return;

		player.userCanKeyMove = new UserCanKeyMove(player);
	}

}
