package com.sunsigne.reversedrebecca.physic;

import com.sunsigne.reversedrebecca.physic.laws.LifeAndDeathLaw;
import com.sunsigne.reversedrebecca.physic.laws.PhysicLaw;
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
		loadOtherOrderedLaws();
	}

	////////// INDEPENDANT ////////////
	
	private void loadIndependantLaws() {
		load(new UpdateLayersLaw());
		load(new WaitforLaw());
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

	////////// OTHER ////////////
	
	private void loadOtherOrderedLaws() {
//		load(new InvulnerabilityFrameLaw());
		load(new LifeAndDeathLaw());
	}

}
