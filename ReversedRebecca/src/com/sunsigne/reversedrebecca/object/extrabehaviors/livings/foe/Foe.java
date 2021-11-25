package com.sunsigne.reversedrebecca.object.extrabehaviors.livings.foe;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.extrabehaviors.Behavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.LivingObject;

public class Foe extends LivingObject {

	public int speed = 6;
	
	public Foe(String name, int x, int y) {
		super(name ,x, y);
		addFoeBehaviors();
	}

	////////// BEHAVIOR ////////////

	public Behavior movingToPlayer = new MovingToPlayer(this);
		
	private void addFoeBehaviors() {
		addBehavior(movingToPlayer);
	}

	////////// COLLISION ////////////
	
	@Override
	public void collidingReaction(CollisionDetector detectorObject) {
		blockPass(detectorObject);
	}

}
