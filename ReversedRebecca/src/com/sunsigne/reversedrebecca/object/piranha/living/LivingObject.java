package com.sunsigne.reversedrebecca.object.piranha.living;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;
import com.sunsigne.reversedrebecca.object.characteristics.PathSearcher;
import com.sunsigne.reversedrebecca.object.characteristics.Pushable;
import com.sunsigne.reversedrebecca.object.characteristics.Pusher;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.object.piranha.living.animation.LivingAnimationHandler;
import com.sunsigne.reversedrebecca.object.piranha.living.characteristics.Health;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public abstract class LivingObject extends PiranhaObject implements Health, Pusher, Pushable, PathSearcher {

	// the only difference between PiranhaObject and LivingObject is that
	// PiranhaObject are not supposed to move by themself.
	// That's it. A homing rolling pin is then a "LivingObject".
	public LivingObject(String name, int x, int y) {
		super(name, x, y);
		loadAnimation();
		setMaxHp(1);
		setFullHp();
	}

	////////// NAME ////////////

	@Override
	public void setName(String name) {
		super.setName(name);
		loadAnimation();
	}

	////////// FACING ////////////

	private boolean flagX, flagY;

	@Override
	public void setMotionless() {
		flagX = true;
		flagY = true;
		super.setMotionless();
	}

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
		if (super.isStunned())
			return true;
		else
			return Health.super.isStunned();
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

	private boolean walkingInPlace;

	public boolean isWalkingInPlace() {
		return walkingInPlace;
	}

	public void setWalkingInPlace(boolean walkingInPlace) {
		this.walkingInPlace = walkingInPlace;
	}

	////////// INVULNERABILITY ////////////

	private boolean invulnerable;

	@Override
	public boolean isInvulnerable() {
		return invulnerable;
	}

	@Override
	public void setInvulnerable(boolean invulnerable) {
		this.invulnerable = invulnerable;
	}

	private boolean recovering;

	@Override
	public boolean isRecovering() {
		return recovering;
	}

	@Override
	public void setRecovering(boolean recovering) {
		this.recovering = recovering;
	}

	////////// MAX HP ////////////

	private int maxhp;

	@Override
	public int getMaxHp() {
		return maxhp;
	}

	@Override
	public void setMaxHp(int maxhp) {
		this.maxhp = maxhp;
	}

	////////// HP ////////////

	private int hp;

	@Override
	public int getHp() {
		return hp;
	}

	@Override
	public void setHp(int hp) {
		this.hp = hp;
	}

	////////// DEATH ////////////

	private boolean registeredAsDead;

	@Override
	public boolean isRegisteredAsDead() {
		return registeredAsDead;
	}

	@Override
	public void registeredAsDead(boolean registeredAsDead) {
		this.registeredAsDead = registeredAsDead;
	}

	@Override
	public Updatable getReplacementUpdatable() {
		return null;
	}

	////////// PATH FINDER ////////////

	@Override
	public boolean mustFollowPath() {
		return true;
	}

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.LIVING;
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

	public BufferedImage getImage() {
		return animation.getImage();
	}

	////////// RENDER ////////////

	private int displayedX, displayedY;

	public void setDisplayXY(int x, int y) {
		int pixel = 16;
		int ratio = Size.M / pixel;
		this.displayedX = x * ratio;
		this.displayedY = y * ratio;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX() + displayedX, getY() + displayedY, getWidth(), getHeight(), null);
		drawHighlight(g, animation.getHighlightImage(), displayedX, displayedY, 0, 0);
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

	private CollisionReactor lastCollidedObject;

	@Override
	public void setLastCollidedObject(CollisionReactor lastCollidedObject) {
		if (lastCollidedObject instanceof Player == false)
			this.lastCollidedObject = lastCollidedObject;
	}

	@Override
	public CollisionReactor getLastCollidedObject() {
		return lastCollidedObject;
	}

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
