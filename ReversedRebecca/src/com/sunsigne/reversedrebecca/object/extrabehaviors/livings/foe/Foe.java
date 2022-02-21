package com.sunsigne.reversedrebecca.object.extrabehaviors.livings.foe;

import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.Behavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.LivingObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.foe.behaviors.GoalIsPlayer;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.foe.behaviors.PushingPlayer;
import com.sunsigne.reversedrebecca.system.Size;

public class Foe extends LivingObject {

	public Foe(int x, int y) {
		this("Gamma", x, y);
	}

	public Foe(String name, int x, int y) {
		super(name, x, y, Size.XS / 5);

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
