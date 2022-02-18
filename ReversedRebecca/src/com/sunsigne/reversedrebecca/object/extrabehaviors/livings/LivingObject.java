package com.sunsigne.reversedrebecca.object.extrabehaviors.livings;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.PathSearcher;
import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.Behavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.behaviors.MoveWhenPushed;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.behaviors.MovingToGoal;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.behaviors.PreventingAccidentalTeleportation;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.behaviors.WalkingRender;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.behaviors.WatchingDirection;

public abstract class LivingObject extends ExtraBehaviorsObject implements PathSearcher, CollisionDetector {

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

	////////// POSITION ////////////

	// WARNING ! this method won't allow to move the living futher than 64 pixel
	// away (by tick) from its current pos
	@Override
	public void setX(int x) {
		super.setX(x);
	}

	// WARNING ! this method won't allow to move the living futher than 64 pixel
	// away (by tick) from its current pos
	@Override
	public void setY(int y) {
		super.setY(y);
	}

	public void teleportTo(int x, int y) {
		removeBehavior(preventingAccidentalTeleportation);
		setX(x);
		setY(y);
		addBehavior(preventingAccidentalTeleportation);
	}

	////////// BEHAVIOR ////////////

	public Behavior preventingAccidentalTeleportation;
	public Behavior watchingDirection;
	public Behavior walkingRender;
	public Behavior movingToGoal;
	public Behavior moveWhenPushed;

	private void addLivingBehaviors() {

		preventingAccidentalTeleportation = new PreventingAccidentalTeleportation(this);
		addBehavior(preventingAccidentalTeleportation);

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
