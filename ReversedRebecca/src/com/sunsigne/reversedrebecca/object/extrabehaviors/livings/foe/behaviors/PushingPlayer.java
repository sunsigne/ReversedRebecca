package com.sunsigne.reversedrebecca.object.extrabehaviors.livings.foe.behaviors;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.Behavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.CollisionBehavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.behaviors.MoveWhenPushed;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.behaviors.Stunned;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.foe.Foe;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player.Player;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player.behaviors.PlayerHealth;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;

public class PushingPlayer implements CollisionBehavior {

	public PushingPlayer(Foe foe) {
		this.foe = foe;
	}

	////////// BEHAVIOR ////////////

	private Foe foe;

	@Override
	public Foe getExtraBehaviorsObject() {
		return foe;
	}

	////////// POSITION ////////////

	@Override
	public int getX() {
		return getExtraBehaviorsObject().getX();
	}

	@Override
	public int getY() {
		return getExtraBehaviorsObject().getY();
	}

	@Override
	public void setX(int x) {
		getExtraBehaviorsObject().setX(x);
	}

	@Override
	public void setY(int y) {
		getExtraBehaviorsObject().setY(y);
	}

	////////// SIZE ////////////

	@Override
	public int getWidth() {
		return getExtraBehaviorsObject().getWidth();
	}

	@Override
	public int getHeight() {
		return getExtraBehaviorsObject().getHeight();
	}

	////////// COLLISION ////////////

	@Override
	public void collidingReaction(CollisionDetector detectorObject) {
		if (!isStunned())
			if (detectorObject instanceof Player) {
				pushPlayer(detectorObject);
				hurtPlayer();
				stunFoe();
			}
		blockPass(detectorObject);
	}

	private boolean isStunned() {
		for (Behavior tempBehavior : foe.getBehaviorList().getList()) {
			if (tempBehavior instanceof Stunned)
				return true;
		}
		return false;
	}

	private void pushPlayer(CollisionDetector detectorObject) {
		Player player = (Player) detectorObject;
		var moveWhenPushed = (MoveWhenPushed) player.moveWhenPushed;
		moveWhenPushed.pushToward(foe.getFacing());
	}

	private void hurtPlayer() {
		PlayerHealth playerHealth = new PlayerFinder().getPlayerHealth();

		if (playerHealth == null)
			return;

		playerHealth.removeHp();
	}

	private void stunFoe() {
		Behavior stunned = new Stunned(foe, foe.movingToPlayer);
		foe.addBehavior(stunned);
	}

}