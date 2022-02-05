package com.sunsigne.reversedrebecca.object.extrabehaviors.livings.foe;

import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.Behavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.LivingObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.foe.behaviors.MovingToPlayer;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.foe.behaviors.PushingPlayer;
import com.sunsigne.reversedrebecca.system.Size;

public class Foe extends LivingObject {

	public int speed = Size.XS/5;

	public Foe(int x, int y) {
		this("Gamma", x, y);
	}
	
	public Foe(String name, int x, int y) {
		super(name, x, y);
		addFoeBehaviors();
	}

	////////// BEHAVIOR ////////////

	public Behavior movingToPlayer;
	public Behavior pushingPlayer;

	private void addFoeBehaviors() {
		
		movingToPlayer = new MovingToPlayer(this);
		addBehavior(movingToPlayer);
		
		pushingPlayer = new PushingPlayer(this);
		addBehavior(pushingPlayer);
	}

}
