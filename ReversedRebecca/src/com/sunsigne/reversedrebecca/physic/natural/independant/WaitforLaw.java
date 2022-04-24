package com.sunsigne.reversedrebecca.physic.natural.independant;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.characteristics.Waitfor;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
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

		GenericListener action = waitfor.getWaitfor().getAction();
		waitfor.setWaitfor(null);
		action.doAction();
	}

	////////// RENDER ////////////

	@Override
	public void beforeObjectRender(Graphics g, Updatable object) {

	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {

	}

}
