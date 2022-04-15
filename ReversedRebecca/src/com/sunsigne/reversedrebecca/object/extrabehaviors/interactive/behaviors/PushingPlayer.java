package com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.behaviors;

import com.sunsigne.reversedrebecca.characteristics.PlayerHealth;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.Behavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.CollisionBehavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.LivingObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.behaviors.MoveWhenPushed;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.behaviors.Stunned;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.players.Player;
import com.sunsigne.reversedrebecca.pattern.TilePos;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.system.Size;

public class PushingPlayer implements CollisionBehavior {

	public PushingPlayer(ExtraBehaviorsObject object, boolean hurtPlayer) {
		this.object = object;
		this.hurtPlayer = hurtPlayer;
	}

	////////// BEHAVIOR ////////////

	private ExtraBehaviorsObject object;

	@Override
	public ExtraBehaviorsObject getExtraBehaviorsObject() {
		return object;
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
	public boolean isBlockingSight() {
		return getExtraBehaviorsObject().isBlockingSight();
	}

	@Override
	public boolean isBlockingPath() {
		return getExtraBehaviorsObject().isBlockingPath();
	}

	private boolean isStunned() {
		for (Behavior tempBehavior : object.getBehaviorList().getList()) {
			if (tempBehavior instanceof Stunned)
				return true;
		}
		return false;
	}

	@Override
	public void collidingReaction(CollisionDetector detectorObject) {
		if (!isStunned())
			if (new PlayerFinder().isPlayerInvolved(detectorObject)) {
				pushPlayer(detectorObject);
				shiftObject();
				hurtPlayer();
				stunObject();
			}
		blockPath(detectorObject);
	}

	private void pushPlayer(CollisionDetector detectorObject) {
		Player player = (Player) detectorObject;
		var moveWhenPushed = (MoveWhenPushed) player.moveWhenPushed;

		if (object instanceof LivingObject)
			moveWhenPushed.pushToward(object.getFacing());
		else
			moveWhenPushed.pushToward(player.getOppositeFacing());
	}

	private void shiftObject() {
		setX(new TilePos().getTilePos(getX(), Size.M));
		setY(new TilePos().getTilePos(getY(), Size.M));
	}

	private boolean hurtPlayer;

	private void hurtPlayer() {
		if (hurtPlayer)
			PlayerHealth.getInstance().removeHp();
	}

	private void stunObject() {
		if (object instanceof LivingObject == false)
			return;

		LivingObject living = (LivingObject) object;
		Behavior stunned = new Stunned(living);
		object.addBehavior(stunned);
	}

}