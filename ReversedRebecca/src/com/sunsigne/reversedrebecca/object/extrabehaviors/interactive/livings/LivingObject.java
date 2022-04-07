package com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.Behavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.behaviors.MoveWhenPushed;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.behaviors.MovingToGoal;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.behaviors.WalkingRender;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.behaviors.WatchingDirection;

public abstract class LivingObject extends ExtraBehaviorsObject implements CollisionDetector {

	public LivingObject(String name, int x, int y, int speed) {
		super(name, x, y);
		this.speed = speed;
		addLivingBehaviors();
	}

	public int speed;

	////////// NAME ////////////

	@Override
	public void setName(String name) {
		super.setName(name);
		if (walkingRender == null)
			return;
		removeBehavior(walkingRender);
		walkingRender = new WalkingRender(this);
		addBehavior(walkingRender);
	}

	////////// BEHAVIOR ////////////

	public Behavior watchingDirection;
	public Behavior walkingRender;
	public Behavior movingToGoal;
	public Behavior moveWhenPushed;

	private void addLivingBehaviors() {

		watchingDirection = new WatchingDirection(this);
		addBehavior(watchingDirection);

		walkingRender = new WalkingRender(this);
		addBehavior(walkingRender);

		movingToGoal = new MovingToGoal(this);
		addBehavior(movingToGoal);

		moveWhenPushed = new MoveWhenPushed(this);
		addBehavior(moveWhenPushed);
	}

	public abstract Behavior[] behaviorToPauseIfStunned();

}
