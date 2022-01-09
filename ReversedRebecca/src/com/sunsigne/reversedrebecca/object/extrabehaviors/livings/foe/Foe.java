package com.sunsigne.reversedrebecca.object.extrabehaviors.livings.foe;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.extrabehaviors.Behavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.LivingObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.MoveWhenPushed;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.Stunned;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player.Player;

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

	private void addFoeBehaviors() {
		addBehavior(movingToPlayer);
	}

	////////// COLLISION ////////////

	@Override
	public void collidingReaction(CollisionDetector detectorObject) {
		if (detectorObject instanceof Player) {
			pushPlayer(detectorObject);
			stunFoe();
		}
		blockPass(detectorObject);
	}

	private void pushPlayer(CollisionDetector detectorObject) {
		Player player = (Player) detectorObject;
		var moveWhenPushed = (MoveWhenPushed) player.moveWhenPushed;
		moveWhenPushed.pushToward(getFacing());
	}
	
	private void stunFoe() {
		Behavior stunned = new Stunned(this, movingToPlayer);
		addBehavior(stunned);
	}

}
