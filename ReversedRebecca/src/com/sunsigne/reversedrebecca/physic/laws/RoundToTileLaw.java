package com.sunsigne.reversedrebecca.physic.laws;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.characteristics.SpeedVariator;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.characteristics.SpeedVariator.SPEEDNESS;
import com.sunsigne.reversedrebecca.pattern.TilePos;
import com.sunsigne.reversedrebecca.system.Size;
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
		if (variator.getSpeedness() == SPEEDNESS.PLAYER_SPEED)
			return;

		int miniX = variator.getX() % Size.M;
		int miniY = variator.getY() % Size.M;

		// if miniX is not possible to reach with known speed
		// OR if object is not moving in this axe
		if (miniX % variator.getSpeed() != 0 || variator.isMotionlessbyX())
			variator.setX(new TilePos().getTilePos(variator.getX(), Size.M));

		if (miniY % variator.getSpeed() != 0 || variator.isMotionlessbyY())
			variator.setY(new TilePos().getTilePos(variator.getY(), Size.M));
	}

	////////// RENDER ////////////

	@Override
	public void beforeObjectRender(Graphics g, Updatable object) {

	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {

	}

}