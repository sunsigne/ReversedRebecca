package com.sunsigne.reversedrebecca.physic.laws.independant;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.characteristics.Waitfor;
import com.sunsigne.reversedrebecca.physic.laws.PhysicLaw;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class WaitforLaw implements PhysicLaw {

	////////// TICK ////////////

	@Override
	public void tick(Updatable object) {
		if (object == null)
			return;

		if (object instanceof Waitfor == false)
			return;

		Waitfor waitfor = (Waitfor) object;

		if (waitfor.getWaitfor() == null)
			return;

		if (waitfor.getWaitfor().canDoAction() == false)
			return;

		waitfor.getWaitfor().doAction();
		waitfor.setWaitfor(null);
	}

	////////// RENDER ////////////

	@Override
	public void beforeObjectRender(Graphics g, Updatable object) {

	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {

	}

}