package com.sunsigne.reversedrebecca.physic.laws;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.piranha.characteristics.SpeedVariator;
import com.sunsigne.reversedrebecca.object.piranha.living.player.PiranhaPlayer;
import com.sunsigne.reversedrebecca.pattern.TilePos;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class RoundToTileLaw implements PhysicLaw {

	////////// TICK ////////////

	@Override
	public void tick(Updatable object) {
		if (object == null)
			return;

		if (object instanceof SpeedVariator == false)
			return;

		SpeedVariator variator = (SpeedVariator) object;

		// player has a special "bypass" speed
		if (variator instanceof PiranhaPlayer)
			return;

		int miniX = variator.getX() % variator.getSize();
		int miniY = variator.getY() % variator.getSize();

		// if miniX is not possible to reach with known speed
		// OR if object is not moving in this axe
		if (miniX % variator.getSpeed() != 0 || variator.isMotionlessbyX())
			variator.setX(new TilePos().getTilePos(variator.getX(), variator.getSize()));

		if (miniY % variator.getSpeed() != 0 || variator.isMotionlessbyY())
			variator.setY(new TilePos().getTilePos(variator.getY(), variator.getSize()));
	}

	////////// RENDER ////////////

	@Override
	public void beforeObjectRender(Graphics g, Updatable object) {

	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {

	}

}