package com.sunsigne.reversedrebecca.physic.laws;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.characteristics.SurVelocity;
import com.sunsigne.reversedrebecca.object.characteristics.Velocity;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class VelocityLaw implements PhysicLaw {

	////////// TICK ////////////

	@Override
	public void tick(Updatable object) {
		if (object == null)
			return;

		SurVelocity surVelocityObject = object instanceof SurVelocity ? (SurVelocity) object : null;
		Velocity velocityObject = object instanceof Velocity ? (Velocity) object : null;

		if (surVelocityObject != null)
			SurVelocityEffect(surVelocityObject);
		else if (velocityObject != null)
			velocityEffect(velocityObject);
	}

	private void SurVelocityEffect(SurVelocity surVelocityObject) {
		if (surVelocityObject.isSurMotionless())
			velocityEffect(surVelocityObject);

		surVelocityObject.setX(surVelocityObject.getX() + surVelocityObject.getSurVelX());
		surVelocityObject.setY(surVelocityObject.getY() + surVelocityObject.getSurVelY());
	}

	private void velocityEffect(Velocity velocityObject) {
		velocityObject.setX(velocityObject.getX() + velocityObject.getVelX());
		velocityObject.setY(velocityObject.getY() + velocityObject.getVelY());
	}

	////////// RENDER ////////////

	@Override
	public void beforeObjectRender(Graphics g, Updatable object) {

	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {

	}

}
