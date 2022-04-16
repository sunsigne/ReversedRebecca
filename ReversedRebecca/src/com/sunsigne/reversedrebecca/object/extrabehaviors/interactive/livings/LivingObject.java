package com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.Behavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.behaviors.PushingPlayer;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.characteristics.PlayerAvoider;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.characteristics.SpeedVariator;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.behaviors.MoveWhenPushed;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.behaviors.MovingToGoal;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.behaviors.StopWhenMeetPlayer;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.behaviors.WalkingRender;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.behaviors.WatchingDirection;
import com.sunsigne.reversedrebecca.system.Size;

public abstract class LivingObject extends ExtraBehaviorsObject
		implements CollisionDetector, PlayerAvoider, SpeedVariator {

	public LivingObject(String name, int x, int y, AVOIDERTYPE playerAvoiderType) {
		super(name, x, y);
		setSpeed(SPEEDTYPE.NORMAL);
		setPlayerAvoiderType(playerAvoiderType);
		addLivingBehaviors();
	}

	////////// NAME ////////////

	@Override
	public void setName(String name) {
		super.setName(name);
		if (walkingRender == null)
			return;
		removeBehavior(walkingRender);
		walkingRender = new WalkingRender(this);
		addBehavior(walkingRender);
	}

	////////// SPEEDNESS ////////////

	private int speed;

	public int getSpeed() {
		return speed;
	}

	// it looks random, but each speed must be a multiple of Size.M
	// not to corrupt pathfinding (except for player outside cutscene)
	public void setSpeed(SPEEDTYPE speedType) {
		switch (speedType) {
		case SLOW:
			speed = Size.M / Size.XS; // 3
			break;
		case NORMAL:
			speed = (Size.M / Size.XS) * 2; // 6
			break;
		case FAST:
			speed = Size.M / 6; // 16
			break;
		case PLAYER_SPEED:
			speed = Size.XL / 16; // 10
			break;
		}
	}

	////////// BEHAVIOR ////////////

	public Behavior watchingDirection;
	public Behavior walkingRender;
	public Behavior movingToGoal;
	public Behavior moveWhenPushed;
	public Behavior avoidingPlayer;

	private void addLivingBehaviors() {

		watchingDirection = new WatchingDirection(this);
		addBehavior(watchingDirection);

		walkingRender = new WalkingRender(this);
		addBehavior(walkingRender);

		movingToGoal = new MovingToGoal(this);
		addBehavior(movingToGoal);

		moveWhenPushed = new MoveWhenPushed(this);
		addBehavior(moveWhenPushed);
	}

	public abstract Behavior[] behaviorToPauseIfStunned();

	////////// COLLISION ////////////

	private AVOIDERTYPE playerAvoiderType;

	@Override
	public AVOIDERTYPE getPlayerAvoiderType() {
		return playerAvoiderType;
	}

	@Override
	public void setPlayerAvoiderType(AVOIDERTYPE playerAvoiderType) {
		if (playerAvoiderType == null)
			playerAvoiderType = AVOIDERTYPE.AROUND;

		this.playerAvoiderType = playerAvoiderType;
		removeBehavior(avoidingPlayer);

		switch (playerAvoiderType) {
		case AROUND:
			avoidingPlayer = null;
			break;
		case STOP:
			avoidingPlayer = new StopWhenMeetPlayer(this);
			break;
		case PUSH:
			avoidingPlayer = new PushingPlayer(this, false);
			break;
		case PUSH_HURT:
			avoidingPlayer = new PushingPlayer(this, true);
			break;
		case PUSH_LEFT:
			avoidingPlayer = new PushingPlayer(this, DIRECTION.LEFT);
			break;
		case PUSH_RIGHT:
			avoidingPlayer = new PushingPlayer(this, DIRECTION.RIGHT);
			break;
		case PUSH_UP:
			avoidingPlayer = new PushingPlayer(this, DIRECTION.UP);
			break;
		case PUSH_DOWN:
			avoidingPlayer = new PushingPlayer(this, DIRECTION.DOWN);
			break;
		}
		addBehavior(avoidingPlayer);
	}

}
