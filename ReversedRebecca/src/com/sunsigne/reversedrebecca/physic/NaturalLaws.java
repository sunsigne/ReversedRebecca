package com.sunsigne.reversedrebecca.physic;

import com.sunsigne.reversedrebecca.physic.laws.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.laws.independant.BlinkingRecoveringLaw;
import com.sunsigne.reversedrebecca.physic.laws.independant.LifeAndDeathLaw;
import com.sunsigne.reversedrebecca.physic.laws.independant.PlayerFinderLaw;
import com.sunsigne.reversedrebecca.physic.laws.independant.UpdateLayersLaw;
import com.sunsigne.reversedrebecca.physic.laws.independant.WaitforLaw;
import com.sunsigne.reversedrebecca.physic.laws.motion.CameraMovingLaw;
import com.sunsigne.reversedrebecca.physic.laws.motion.CollisionLaw;
import com.sunsigne.reversedrebecca.physic.laws.motion.MoveTowardGoalLaw;
import com.sunsigne.reversedrebecca.physic.laws.motion.PathFindingLaw;
import com.sunsigne.reversedrebecca.physic.laws.motion.PlayerBlockingAvoiderLaw;
import com.sunsigne.reversedrebecca.physic.laws.motion.RoundToTileLaw;
import com.sunsigne.reversedrebecca.physic.laws.motion.VelocityLaw;

public class NaturalLaws {

	private void load(PhysicLaw physicLaw) {
		PhysicList.getList().addObject(physicLaw);
	}

	public void loadRessources() {
		loadIndependantLaws();
		loadMotionLaws();
	}

	////////// INDEPENDANT ////////////

	private void loadIndependantLaws() {
		load(new PlayerFinderLaw());
		load(new UpdateLayersLaw());
		load(new WaitforLaw());
		load(new BlinkingRecoveringLaw());
		load(new LifeAndDeathLaw());
	}

	////////// MOTION ////////////

	// must be added in this very specific order
	private void loadMotionLaws() {
		load(new VelocityLaw());
		load(new MoveTowardGoalLaw());
		load(new CollisionLaw());
		load(new RoundToTileLaw());
		load(new PlayerBlockingAvoiderLaw());
		load(new PathFindingLaw());
		load(new CameraMovingLaw());
	}

}
