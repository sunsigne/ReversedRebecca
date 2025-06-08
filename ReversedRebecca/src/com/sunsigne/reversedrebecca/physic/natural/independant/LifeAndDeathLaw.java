package com.sunsigne.reversedrebecca.physic.natural.independant;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.menu.GameOverScreen;
import com.sunsigne.reversedrebecca.object.piranha.living.characteristics.Feeling.CONDITION;
import com.sunsigne.reversedrebecca.object.piranha.living.characteristics.Health;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.ressources.Save;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.DifficultyOption;
import com.sunsigne.reversedrebecca.system.DifficultyOption.GAME_DIFFICULTY;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;
import com.sunsigne.reversedrebecca.world.World;

public class LifeAndDeathLaw extends IndependantLaw {

	////////// INDEPENDANT LAW ////////////

	private static IndependantLaw independantLaw = new LifeAndDeathLaw();

	@Override
	public IndependantLaw getIndependantLaw() {
		return independantLaw;
	}

	////////// TICK ////////////

	@Override
	public void tick(Updatable object) {
		if (object instanceof Health == false)
			return;

		Health health = (Health) object;

		if (health.isDead() == false)
			return;

		if (health.isRegisteredAsDead())
			return;

		kill(health);
	}

	public static void kill(Health health) {
		health.setStunned(true);
		health.setCondition(CONDITION.KO);
		health.registeredAsDead(true);
		health.sendToGround();

		if (health instanceof Player)
			loadGameOverScreen();
	}

	public static void loadGameOverScreen() {
		if (DifficultyOption.getDifficulty() == GAME_DIFFICULTY.HARD)
			new Save().resetProgression();

		World.get().freeze(true);
		GameOverScreen menu = new GameOverScreen();
		LAYER.MENU.addObject(menu);
	}

	////////// RENDER ////////////

	@Override
	public void beforeObjectRender(Graphics g, Updatable object) {

	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {

	}

}
