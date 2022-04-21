package com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.foe;

import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.Behavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.behaviors.BlockingPath;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.LivingObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.foe.behaviors.GoalIsPlayer;

public class Foe extends LivingObject {

	public Foe(String name, int x, int y) {
		super(name, x, y, AVOIDERTYPE.PUSH_HURT);
		addFoeBehaviors();
	}

	////////// BEHAVIOR ////////////

	public Behavior blockingPath;
	public Behavior goalIsPlayer;

	private void addFoeBehaviors() {

		blockingPath = new BlockingPath(this);
		addBehavior(blockingPath);

		goalIsPlayer = new GoalIsPlayer(this);
		addBehavior(goalIsPlayer);
	}

}
