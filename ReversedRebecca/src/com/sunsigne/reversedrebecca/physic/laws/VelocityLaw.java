package com.sunsigne.reversedrebecca.physic.laws;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.characteristics.Velocity;
import com.sunsigne.reversedrebecca.physic.PhysicList;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class VelocityLaw implements PhysicLaw {

	static {
		PhysicList.getList().getList().add(0, new VelocityLaw());
	}
	
	////////// TICK ////////////

	@Override
	public void tick(Updatable object) {
		if (object == null)
			return;

		Velocity velicityObject = null;

		if (object instanceof Velocity)
			velicityObject = (Velocity) object;
		else
			return;

		velicityObject.setX(velicityObject.getX() + velicityObject.getVelX());
		velicityObject.setY(velicityObject.getY() + velicityObject.getVelY());
	}

	////////// RENDER ////////////

	@Override
	public void beforeObjectRender(Graphics g, Updatable object) {

	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {

	}

}
