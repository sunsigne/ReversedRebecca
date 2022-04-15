package com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.Behavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.behaviors.PushingPlayer;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.behaviors.MoveWhenPushed;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.behaviors.MovingToGoal;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.behaviors.StopWhenMeetPlayer;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.behaviors.WalkingRender;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.behaviors.WatchingDirection;
import com.sunsigne.reversedrebecca.system.Size;

public abstract class LivingObject extends ExtraBehaviorsObject implements CollisionDetector, PlayerAvoider {

	public LivingObject(String name, int x, int y, AVOIDERTYPE playerAvoiderType) {
		this(name, x, y, Size.XS / 10, Size.XS / 5, playerAvoiderType);
	}

	public LivingObject(String name, int x, int y, int walking_speed, int running_speed,
			AVOIDERTYPE playerAvoiderType) {
		super(name, x, y);
		this.running = true;
		this.walking_speed = walking_speed;
		this.running_speed = running_speed;
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

	////////// SPEED ////////////

	private int walking_speed;
	private int running_speed;

	private boolean running;

	public void setRunning(boolean running) {
		this.running = running;
	}

	public int getSpeed() {
		if (running)
			return running_speed;
		else
			return walking_speed;
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
			avoidingPlayer = new StopWhenMeetPlayer(this);
			break;
		case PUSH:
			avoidingPlayer = new PushingPlayer(this, false);
			break;
		case PUSH_HURT:
			avoidingPlayer = new PushingPlayer(this, true);
			break;
		case STOP:
			avoidingPlayer = new StopWhenMeetPlayer(this);
			break;
		}
		addBehavior(avoidingPlayer);
	}

}
