package com.sunsigne.reversedrebecca.world.controllers;

import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;

public class UserCanInputHurt extends WorldControllers {

	private static WorldControllers worldKeyboard = new UserCanInputHurt();

	@Override
	public WorldControllers getWorldControllers() {
		return worldKeyboard;
	}

	////////// KEYBOARD ////////////

	@Override
	public boolean devOnly() {
		return true;
	}

	@Override
	public void inputPressed(int key, int button) {
		if (key == KeyEvent.VK_COLON) {
			Player player = new PlayerFinder().getPlayer();

			if (player == null || player.isRegisteredAsDead())
				return;

			player.removeHp(1);
			new SoundTask().playSound(SOUNDTYPE.SOUND, "hit_medium");
		}
	}

	@Override
	public void inputReleased(int key, int button) {

	}

}
