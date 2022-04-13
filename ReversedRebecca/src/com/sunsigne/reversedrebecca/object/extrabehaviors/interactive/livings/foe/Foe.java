package com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.foe;

import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.Behavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.LivingObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.foe.behaviors.GoalIsPlayer;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.foe.behaviors.PushingPlayer;

public class Foe extends LivingObject {

	public Foe(String name, int x, int y) {
		super(name, x, y);
		addFoeBehaviors();
	}

	////////// BEHAVIOR ////////////

	public Behavior goalIsPlayer;
	public Behavior pushingPlayer;

	private void addFoeBehaviors() {

		goalIsPlayer = new GoalIsPlayer(this);
		addBehavior(goalIsPlayer);

		pushingPlayer = new PushingPlayer(this);
		addBehavior(pushingPlayer);
	}

	@Override
	public Behavior[] behaviorToPauseIfStunned() {
		return new Behavior[] { movingToGoal };
	}

}
