package com.sunsigne.reversedrebecca.world.controllers;

import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.physic.natural.independant.LifeAndDeathLaw;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;

public class UserCanInputDie extends WorldControllers {

	private static WorldControllers worldKeyboard = new UserCanInputDie();

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
		if (key == KeyEvent.VK_EXCLAMATION_MARK) {
			Player player = new PlayerFinder().getPlayer();
			player.removeHp(player.getMaxHp());

			new SoundTask().playSound(SOUNDTYPE.SOUND, "hit_large");
			LifeAndDeathLaw.kill(player);
		}
	}

	@Override
	public void inputReleased(int key, int button) {

	}

}
