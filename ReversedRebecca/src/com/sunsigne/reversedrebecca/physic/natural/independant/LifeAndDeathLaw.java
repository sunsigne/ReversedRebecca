package com.sunsigne.reversedrebecca.physic.natural.independant;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.piranha.living.characteristics.Feeling.CONDITION;
import com.sunsigne.reversedrebecca.object.piranha.living.characteristics.Health;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

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

		health.setStunned(true);
		health.setCondition(CONDITION.KO);
		health.registeredAsDead(true);
		health.sendToGround();
	}

	////////// RENDER ////////////

	@Override
	public void beforeObjectRender(Graphics g, Updatable object) {

	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {

	}

}
