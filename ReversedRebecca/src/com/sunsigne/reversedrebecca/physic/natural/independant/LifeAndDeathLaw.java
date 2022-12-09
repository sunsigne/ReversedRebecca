package com.sunsigne.reversedrebecca.physic.natural.independant;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.piranha.living.characteristics.Feeling.CONDITION;
import com.sunsigne.reversedrebecca.object.piranha.living.characteristics.Health;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;
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

		Handler current_handler = object.getHandler();
		Handler current_ground_handler = getGroundLayerFromObject(current_handler, object);

		current_handler.removeObject(object);
		current_ground_handler.addObject(object);
	}

	private Handler getGroundLayerFromObject(Handler handler, Updatable object) {
		LAYER ground_layer = LAYER.GROUND;

		for (LAYER tempLayer : LAYER.values()) {
			if (handler != tempLayer.getHandler())
				ground_layer = tempLayer;

			else
				break;
		}

		return ground_layer.getHandler();
	}

	////////// RENDER ////////////

	@Override
	public void beforeObjectRender(Graphics g, Updatable object) {

	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {

	}

}
