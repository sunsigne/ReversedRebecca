package com.sunsigne.reversedrebecca.world.controllers;

import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;

public class UserCanInputDie extends WorldControllers {

	private static WorldControllers worldKeyboard = new UserCanInputDie();

	@Override
	public WorldControllers getWorldControllers() {
		return worldKeyboard;
	}

	////////// KEYBOARD ////////////

	@Override
	public void inputPressed(int key, int button) {
		if (key == KeyEvent.VK_EXCLAMATION_MARK) {
			Player player = new PlayerFinder().getPlayer();
			player.removeHp(player.getMaxHp());
		}

	}

	@Override
	public void inputReleased(int key, int button) {

	}

}
