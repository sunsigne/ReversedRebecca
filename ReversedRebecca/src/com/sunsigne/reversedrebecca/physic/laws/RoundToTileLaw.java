package com.sunsigne.reversedrebecca.physic.laws;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.characteristics.SpeedVariator;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.characteristics.SpeedVariator.SPEEDTYPE;
import com.sunsigne.reversedrebecca.pattern.TilePos;
import com.sunsigne.reversedrebecca.physic.PhysicList;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class RoundToTileLaw implements PhysicLaw {

	////////// PHYSIC LAW ////////////

	public RoundToTileLaw() {
		if (PhysicList.getList().containsObject(physicLaw))
			return;

		// this law must rules right before PathFindingLaw
		PhysicLaw pathfinding = PhysicList.getList().getObject(new PathFindingLaw());
		int index = PhysicList.getList().getList().indexOf(pathfinding);
		PhysicList.getList().getList().add(index, this);
	}

	private static PhysicLaw physicLaw = new RoundToTileLaw();

	@Override
	public PhysicLaw getPhysicLaw() {
		return physicLaw;
	}

	////////// TICK ////////////

	@Override
	public void tick(Updatable object) {
		if (object == null)
			return;

		if (object instanceof SpeedVariator == false)
			return;

		SpeedVariator variator = (SpeedVariator) object;

		// player has a special "bypass" speed
		if (variator.getSpeedType() == SPEEDTYPE.PLAYER_SPEED)
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