package com.sunsigne.reversedrebecca.object.extrabehaviors.livings.foe;

import com.sunsigne.reversedrebecca.object.extrabehaviors.Behavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.LivingObject;

public class Foe extends LivingObject {

	public int speed = 6;

	public Foe(int x, int y) {
		this("Gamma", x, y);
	}
	
	public Foe(String name, int x, int y) {
		super(name, x, y);
		addFoeBehaviors();
	}

	////////// BEHAVIOR ////////////

	public Behavior movingToPlayer = new MovingToPlayer(this);
	public Behavior pushingPlayer = new PushingPlayer(this);

	private void addFoeBehaviors() {
		addBehavior(movingToPlayer);
		addBehavior(pushingPlayer);
	}

}
