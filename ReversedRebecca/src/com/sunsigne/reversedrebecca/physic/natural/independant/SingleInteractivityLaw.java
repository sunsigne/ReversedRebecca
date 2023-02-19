package com.sunsigne.reversedrebecca.physic.natural.independant;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.characteristics.interactive.Interactive;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;
import com.sunsigne.reversedrebecca.world.World;

public class SingleInteractivityLaw extends IndependantLaw {

	////////// INDEPENDANT LAW ////////////

	private static IndependantLaw independantLaw = new SingleInteractivityLaw();

	@Override
	public IndependantLaw getIndependantLaw() {
		return independantLaw;
	}

	////////// INTERACTIVE ////////////

	private static Interactive singleInteractor;

	public static Interactive getCurrentInteractor() {
		return singleInteractor;
	}

	////////// TICK ////////////

	@Override
	public void tick(Updatable object) {
		if (object instanceof World)
			singleInteractor = null;

		if (object instanceof Interactive == false)
			return;

		Interactive interactive = (Interactive) object;
		if (interactive.canPlayerInterfact() == false)
			return;

		if (interactive.getTripleAction() != null && interactive.getTripleAction().cannotDoAnyAction() == false)
			singleInteractor = interactive;
	}

	////////// RENDER ////////////

	@Override
	public void beforeObjectRender(Graphics g, Updatable object) {

	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {

	}

}
