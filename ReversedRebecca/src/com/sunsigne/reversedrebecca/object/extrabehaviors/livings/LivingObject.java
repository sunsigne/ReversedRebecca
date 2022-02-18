package com.sunsigne.reversedrebecca.object.extrabehaviors.livings;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.Behavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.behaviors.MoveWhenPushed;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.behaviors.MovingToGoal;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.behaviors.WalkingRender;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.behaviors.WatchingDirection;

public abstract class LivingObject extends ExtraBehaviorsObject implements CollisionDetector {

	public LivingObject(String name, int x, int y, int speed) {
		super(x, y);
		this.speed = speed;
		this.name = name.toLowerCase();
		addLivingBehaviors();
	}

	public int speed;

	////////// NAME ////////////

	private String name;

	public String getName() {
		return name;
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

}
