package com.sunsigne.reversedrebecca.characteristics.drunk;

import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;

public class DrunkTask {

	private static int drunk;

	public static int getDrunk() {
		return drunk;
	}

	public void setDrunk(int drunk) {
		DrunkTask.drunk = drunk;
		updateDeath();
	}

	public void addDrunk() {
		addDrunk(1);
	}

	public void addDrunk(int amount) {
		setDrunk(getDrunk() + amount);
		updateDeath();
	}

	public void removeDrunk() {
		removeDrunk(1);
	}

	public void removeDrunk(int amount) {
		setDrunk(getDrunk() - amount);
		updateDeath();
	}

	public void updateDeath() {
		if (getDrunk() < 10)
			return;

		Player player = new PlayerFinder().getPlayer();
		if (player == null)
			return;

		new SoundTask().playSound(SOUNDTYPE.SOUND, "death");
		player.removeHp(999);
	}

}
