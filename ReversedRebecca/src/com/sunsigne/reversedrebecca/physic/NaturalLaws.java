package com.sunsigne.reversedrebecca.physic;

import com.sunsigne.reversedrebecca.physic.laws.CameraMovingLaw;
import com.sunsigne.reversedrebecca.physic.laws.CollisionLaw;
import com.sunsigne.reversedrebecca.physic.laws.MoveTowardGoalLaw;
import com.sunsigne.reversedrebecca.physic.laws.PathFindingLaw;
import com.sunsigne.reversedrebecca.physic.laws.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.laws.PlayerBlockingAvoiderLaw;
import com.sunsigne.reversedrebecca.physic.laws.RoundToTileLaw;
import com.sunsigne.reversedrebecca.physic.laws.UpdateLayersLaw;
import com.sunsigne.reversedrebecca.physic.laws.VelocityLaw;

public class NaturalLaws {

	public void loadRessources() {
		// This law as nothing to do close or far with movement
		load(new UpdateLayersLaw());

		// (following rules CANNOT be added in another order)

		// An object can move
		load(new VelocityLaw());

		// An object can move by itself, following path.
		load(new MoveTowardGoalLaw());

		// Moving, an object that meets an obstacle is shifted.
		load(new CollisionLaw());

		// An object is repositionnated properly for calculating pathfinding.
		load(new RoundToTileLaw());

		// Determinate if an object must count the player in calcul of pathfinding.
		load(new PlayerBlockingAvoiderLaw());

		// Calculating path of the object for its next move.
		load(new PathFindingLaw());

		// Calculating the "definitive" position for Camera (for the tick).
		load(new CameraMovingLaw());
	}

	private void load(PhysicLaw physicLaw) {
		PhysicList.getList().addObject(physicLaw);
	}

}
