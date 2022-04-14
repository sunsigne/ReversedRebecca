package com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.foe;

import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.Behavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.LivingObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.foe.behaviors.GoalIsPlayer;

public class Foe extends LivingObject {

	public Foe(String name, int x, int y) {
		super(name, x, y, COLLISIONTYPE.PUSH_HURT);
		addFoeBehaviors();
	}

	////////// BEHAVIOR ////////////

	public Behavior goalIsPlayer;

	private void addFoeBehaviors() {

		goalIsPlayer = new GoalIsPlayer(this);
		addBehavior(goalIsPlayer);
	}

	@Override
	public Behavior[] behaviorToPauseIfStunned() {
		return new Behavior[] { movingToGoal };
	}

}
