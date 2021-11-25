package com.sunsigne.reversedrebecca.object.extrabehaviors.livings.foe;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.extrabehaviors.Behavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.LivingObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.MoveWhenPushed;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player.Player;

public class Foe extends LivingObject {

	public int speed = 6;

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
		if (detectorObject instanceof Player)
			pushPlayer(detectorObject);
		blockPass(detectorObject);
	}

	private void pushPlayer(CollisionDetector detectorObject) {
		Player player = (Player) detectorObject;
		// stun();
		var moveWhenPushed = (MoveWhenPushed) player.moveWhenPushed;
		moveWhenPushed.pushToward(getFacing());
	}

}
