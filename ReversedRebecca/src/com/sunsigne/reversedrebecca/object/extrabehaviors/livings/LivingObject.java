package com.sunsigne.reversedrebecca.object.extrabehaviors.livings;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.Facing;
import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.Behavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.behaviors.MoveWhenPushed;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.behaviors.WalkingRender;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.behaviors.WatchingDirection;

public abstract class LivingObject extends ExtraBehaviorsObject implements Facing, CollisionDetector {

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
