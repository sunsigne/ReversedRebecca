package com.sunsigne.reversedrebecca.physic.natural.correlated;

import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicList;

public class CorrelatedLaw {

	private void load(PhysicLaw physicLaw) {
		PhysicList.getList().addObject(physicLaw);
	}

	// must be added in this very specific order
	public void loadRessources() {
		load(new VelocityLaw());
		load(new MoveTowardGoalLaw());
		load(new CollisionLaw());
		load(new RoundToTileLaw());
		load(new PlayerBlockingAvoiderLaw());
		load(new PathFindingLaw());
		load(new CameraMovingLaw());
		load(new CameraShakingLaw());
	}

}
