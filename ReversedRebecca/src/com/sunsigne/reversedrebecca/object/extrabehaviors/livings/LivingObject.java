package com.sunsigne.reversedrebecca.object.extrabehaviors.livings;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;
import com.sunsigne.reversedrebecca.object.extrabehaviors.Behavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;

public abstract class LivingObject extends ExtraBehaviorsObject implements CollisionDetector, CollisionReactor {

	public LivingObject(String name, int x, int y) {
		super(name, x, y);
		addLivingBehaviors();
	}

	////////// BEHAVIOR ////////////
	
	public Behavior watchingDirection = new WatchingDirection(this);
	public Behavior walkingRender = new WalkingRender(this);
	public Behavior moveWhenPushed = new MoveWhenPushed(this);
		
	private void addLivingBehaviors() {
		addBehavior(watchingDirection);
		addBehavior(walkingRender);
		addBehavior(moveWhenPushed);
	}

}
