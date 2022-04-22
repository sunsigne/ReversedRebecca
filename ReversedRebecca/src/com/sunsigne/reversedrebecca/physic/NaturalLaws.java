package com.sunsigne.reversedrebecca.physic;

import com.sunsigne.reversedrebecca.physic.laws.CameraMovingLaw;
import com.sunsigne.reversedrebecca.physic.laws.CollisionLaw;
import com.sunsigne.reversedrebecca.physic.laws.MoveTowardGoalLaw;
import com.sunsigne.reversedrebecca.physic.laws.PathFindingLaw;
import com.sunsigne.reversedrebecca.physic.laws.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.laws.PlayerBlockingAvoiderLaw;
import com.sunsigne.reversedrebecca.physic.laws.RoundToTileLaw;
import com.sunsigne.reversedrebecca.physic.laws.VelocityLaw;
import com.sunsigne.reversedrebecca.physic.laws.independant.LifeAndDeathLaw;
import com.sunsigne.reversedrebecca.physic.laws.independant.UpdateLayersLaw;
import com.sunsigne.reversedrebecca.physic.laws.independant.WaitforLaw;

public class NaturalLaws {

	public void loadRessources() {
		loadIndependantLaws();
		loadSpecificOrderLaws();
	}

	private void loadIndependantLaws() {
		load(new UpdateLayersLaw());
		load(new WaitforLaw());
		load(new LifeAndDeathLaw());
	}

	private void loadSpecificOrderLaws() {
		load(new VelocityLaw());
		load(new MoveTowardGoalLaw());
		load(new CollisionLaw());
		load(new RoundToTileLaw());
		load(new PlayerBlockingAvoiderLaw());
		load(new PathFindingLaw());
		load(new CameraMovingLaw());
	}

	private void load(PhysicLaw physicLaw) {
		PhysicList.getList().addObject(physicLaw);
	}

}
