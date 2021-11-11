package com.sunsigne.reversedrebecca.object;

import com.sunsigne.reversedrebecca.object.behaviors.Behavior;
import com.sunsigne.reversedrebecca.object.behaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.object.behaviors.WalkingRender;
import com.sunsigne.reversedrebecca.object.behaviors.WatchingDirection;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;

public abstract class LivingObject extends ExtraBehaviorsObject implements CollisionDetector, CollisionReactor {

	public LivingObject(String name, int x, int y) {
		super(name, x, y);
		addLivingBehaviors();
	}

	////////// BEHAVIOR ////////////
	
	public Behavior watchingDirection = new WatchingDirection(this);
	public Behavior walkingRender = new WalkingRender(this);
		
	private void addLivingBehaviors() {
		addBehavior(watchingDirection);
		addBehavior(walkingRender);
	}
	
	////////// COLLISION ////////////
	
	@Override
	public void collidingReaction(CollisionDetector detectorObject) {
		blockPass(detectorObject);
	}
		
}
