package com.sunsigne.reversedrebecca.physic.laws.motion;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.characteristics.Velocity;
import com.sunsigne.reversedrebecca.physic.laws.PhysicLaw;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class VelocityLaw implements PhysicLaw {

	////////// TICK ////////////

	@Override
	public void tick(Updatable object) {
		if (object == null)
			return;

		if (object instanceof Velocity == false)
			return;

		Velocity velocity = (Velocity) object;

		velocity.setX(velocity.getX() + velocity.getVelX());
		velocity.setY(velocity.getY() + velocity.getVelY());
	}

	////////// RENDER ////////////

	@Override
	public void beforeObjectRender(Graphics g, Updatable object) {

	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {

	}

}
