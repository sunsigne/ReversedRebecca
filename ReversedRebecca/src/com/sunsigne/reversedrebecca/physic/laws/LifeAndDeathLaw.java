package com.sunsigne.reversedrebecca.physic.laws;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.characteristics.PlayerHealth;
import com.sunsigne.reversedrebecca.object.piranha.living.characteristics.Feeling.CONDITION;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class LifeAndDeathLaw implements PhysicLaw {

	////////// TICK ////////////

	@Override
	public void tick(Updatable object) {
		if (object == null)
			return;

		if (object instanceof Player == false)
			return;

		Player player = (Player) object;

		if (PlayerHealth.getInstance().isDead())
			player.setCondition(CONDITION.KO);
	}

	////////// RENDER ////////////

	@Override
	public void beforeObjectRender(Graphics g, Updatable object) {

	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {

	}

}
