package com.sunsigne.reversedrebecca.world.keyboard;

import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;

public class UserCanKeyDie extends WorldKeyboard {

	private static WorldKeyboard worldKeyboard = new UserCanKeyDie();

	@Override
	public WorldKeyboard getWorldKeyboard() {
		return worldKeyboard;
	}

	////////// KEYBOARD ////////////

	@Override
	public void keyPressed(int key) {
		if (key == KeyEvent.VK_EXCLAMATION_MARK) {
			Player player = new PlayerFinder().getPlayer();
			player.removeHp(player.getMaxHp());
		}

	}

	@Override
	public void keyReleased(int key) {

	}

}
