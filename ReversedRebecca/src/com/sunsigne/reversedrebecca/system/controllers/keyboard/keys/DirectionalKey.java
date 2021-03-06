package com.sunsigne.reversedrebecca.system.controllers.keyboard.keys;

import com.sunsigne.reversedrebecca.object.piranha.living.player.UserKeyMovePlayer;

public interface DirectionalKey extends Key {

	default void updateKeyMoveKeys() {
		UserKeyMovePlayer.getInstance().refreshDirectionKeys();
	}

}
