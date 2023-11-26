package com.sunsigne.reversedrebecca.physic.natural.independant;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.characteristics.interactive.Interactive;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class HightlightAbovenessLaw extends IndependantLaw {

	////////// INDEPENDANT LAW ////////////

	private static IndependantLaw independantLaw = new HightlightAbovenessLaw();

	@Override
	public IndependantLaw getIndependantLaw() {
		return independantLaw;
	}

	////////// TICK ////////////

	@Override
	public void tick(Updatable object) {

	}

	////////// RENDER ////////////

	@Override
	public void beforeObjectRender(Graphics g, Updatable object) {

	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {
		if (object instanceof Player == false)
			return;

		Interactive interactive = SingleInteractivityLaw.getCurrentInteractor();
		
		if(interactive == null)
			return;
		
		if (interactive.isHighlightAbovePlayer())
			interactive.render(g);
	}

}
