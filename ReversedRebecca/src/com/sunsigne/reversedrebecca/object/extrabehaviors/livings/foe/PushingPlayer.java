package com.sunsigne.reversedrebecca.object.extrabehaviors.livings.foe;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.extrabehaviors.Behavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.MoveWhenPushed;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.Stunned;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player.Player;

public class PushingPlayer implements Behavior {

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

	////////// TICK ////////////

	@Override
	public void tick() {

	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {

	}

	////////// COLLISION ////////////

	@Override
	public void collidingReaction(CollisionDetector detectorObject) {
		if (!isStunned())
			if (detectorObject instanceof Player) {
				pushPlayer(detectorObject);
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

	private void stunFoe() {
		Behavior stunned = new Stunned(foe, foe.movingToPlayer);
		foe.addBehavior(stunned);
	}

}