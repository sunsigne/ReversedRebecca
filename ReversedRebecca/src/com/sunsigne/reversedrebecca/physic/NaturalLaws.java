package com.sunsigne.reversedrebecca.physic;

import com.sunsigne.reversedrebecca.physic.laws.CollisionLaw;
import com.sunsigne.reversedrebecca.physic.laws.MoveTowardGoalLaw;
import com.sunsigne.reversedrebecca.physic.laws.PathFindingLaw;
import com.sunsigne.reversedrebecca.physic.laws.RoundToTileLaw;
import com.sunsigne.reversedrebecca.physic.laws.UpdateLayersLaw;
import com.sunsigne.reversedrebecca.physic.laws.VelocityLaw;

public class NaturalLaws {

	public void loadRessources() {
		// This law as nothing to do close or far with movement
		PhysicList.getList().addObject(new UpdateLayersLaw());

		// (following rules CANNOT be added in another order)

		// An object can move
		PhysicList.getList().addObject(new VelocityLaw());

		// An object can move by itself, following path.
		PhysicList.getList().addObject(new MoveTowardGoalLaw());

		// Moving, an object that meets an obstacle is shifted.
		PhysicList.getList().addObject(new CollisionLaw());

		// An object is repositionnated properly for calculating pathfinding.
		PhysicList.getList().addObject(new RoundToTileLaw());

		// Calculating path of the object for its next move.
		PhysicList.getList().addObject(new PathFindingLaw());
	}

}
