package com.sunsigne.reversedrebecca.object.piranha.living;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.characteristics.PlayerAvoider;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.object.piranha.characteristics.Feeling;
import com.sunsigne.reversedrebecca.object.piranha.characteristics.Pushable;
import com.sunsigne.reversedrebecca.object.piranha.characteristics.Pusher;
import com.sunsigne.reversedrebecca.object.piranha.living.animation.LivingAnimationHandler;

public abstract class LivingObject extends PiranhaObject implements Feeling, CollisionDetector, PlayerAvoider {

	// the only difference between PiranhaObject and LivingObject is that
	// PiranhaObject are not supposed to move by themself.
	// That's it.
	public LivingObject(String name, int x, int y) {
		super(name, x, y);
		loadAnimation();
		setPlayerAvoiderType(AVOIDERTYPE.STOP);
	}

	////////// NAME ////////////

	@Override
	public void setName(String name) {
		super.setName(name);
		loadAnimation();
	}

	////////// FACING ////////////

	private boolean flagX, flagY;

	protected void updateWatchingDirection() {
		if (isMotionlessbyX())
			flagX = false;
		if (isMotionlessbyY())
			flagY = false;

		if (!flagY && getVelX() < 0) {
			setFacing(DIRECTION.LEFT);
			flagX = true;
		}
		if (!flagY && getVelX() > 0) {
			setFacing(DIRECTION.RIGHT);
			flagX = true;
		}

		if (!flagX && getVelY() < 0) {
			setFacing(DIRECTION.UP);
			flagY = true;
		}
		if (!flagX && getVelY() > 0) {
			setFacing(DIRECTION.DOWN);
			flagY = true;
		}
	}

	////////// STUNNABLE ////////////

	@Override
	public boolean isStunned() {
		if (getCondition() == CONDITION.KO)
			return true;
		else
			return super.isStunned();
	}

	////////// CONDITION ////////////

	private CONDITION condition = CONDITION.GOOD;

	@Override
	public CONDITION getCondition() {
		return condition;
	}

	@Override
	public void setCondition(CONDITION condition) {
		this.condition = condition;
	}

	////////// PATH FINDER ////////////

	@Override
	public boolean mustFollowPath() {
		return true;
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		if (!isStunned())
			updateWatchingDirection();

		animation.run();
	}

	////////// TEXTURE ////////////

	private LivingAnimationHandler animation;

	private void loadAnimation() {
		animation = new LivingAnimationHandler(this);
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(animation.getImage(), getX(), getY(), getWidth(), getHeight(), null);
	}

	////////// PLAYER AVOIDER ////////////

	private boolean playerBlockingAvoider;

	@Override
	public boolean isPlayerBlockingAvoider() {
		return playerBlockingAvoider;
	}

	@Override
	public void setPlayerBlockingAvoider(boolean playerBlockingAvoider) {
		this.playerBlockingAvoider = playerBlockingAvoider;
	}

	private AVOIDERTYPE avoiderType;

	@Override
	public AVOIDERTYPE getPlayerAvoiderType() {
		return avoiderType;
	}

	@Override
	public void setPlayerAvoiderType(AVOIDERTYPE playerAvoiderType) {
		this.avoiderType = playerAvoiderType;
		PlayerAvoider.super.setPlayerAvoiderType(playerAvoiderType);
	}

	////////// PUSHER ////////////

	private boolean hurtWhenPushing = false;

	@Override
	public boolean hurtWhenPushing() {
		return hurtWhenPushing;
	}

	@Override
	public void setHurtWhenPushing(boolean hurtWhenPushing) {
		this.hurtWhenPushing = hurtWhenPushing;
	}

	private PUSHING_DIRECTION pushingDirection;

	@Override
	public PUSHING_DIRECTION getPushingDirection() {
		return pushingDirection;
	}

	@Override
	public void setPushingDirection(PUSHING_DIRECTION pushingDirection) {
		this.pushingDirection = pushingDirection;
	}

	////////// COLLISION ////////////

	@Override
	public void collidingReaction(CollisionDetector detectorObject) {
		if (detectorObject instanceof Pushable == false) {
			defaultCollindingReaction(detectorObject);
			return;
		}

		if (getPushingDirection() == null) {
			if (detectorObject instanceof Pusher == false) {
				defaultCollindingReaction(detectorObject);
				return;
			} else if (((Pusher) detectorObject).getPushingDirection() == null) {
				defaultCollindingReaction(detectorObject);
				return;
			}
		}

		Pushable pushable = (Pushable) detectorObject;
		push(pushable);
	}

	protected void defaultCollindingReaction(CollisionDetector detectorObject) {
		blockPath(detectorObject);
	}

}
