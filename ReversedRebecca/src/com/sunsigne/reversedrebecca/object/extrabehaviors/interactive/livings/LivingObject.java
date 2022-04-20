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
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.behaviors.WatchingDirection;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.behaviors.render.SickRender;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.behaviors.render.WalkingRender;

public abstract class LivingObject extends ExtraBehaviorsObject
		implements CollisionDetector, Feeling, PlayerAvoider, SpeedVariator {

	public LivingObject(String name, int x, int y, AVOIDERTYPE playerAvoiderType) {
		super(name, x, y);
		setCondition(CONDITION.GOOD);
		setSpeedType(SPEEDTYPE.NORMAL);
		setPlayerAvoiderType(playerAvoiderType);
		addLivingBehaviors();
	}

	////////// NAME ////////////

	@Override
	public void setName(String name) {
		super.setName(name);
		setCondition(getCondition());
	}

	////////// SPEEDNESS ////////////

	private SPEEDTYPE speedType;

	@Override
	public SPEEDTYPE getSpeedType() {
		return speedType;
	}

	@Override
	public void setSpeedType(SPEEDTYPE speedType) {
		this.speedType = speedType;
	}

	////////// BEHAVIOR ////////////

	public Behavior livingRender;
	public Behavior avoidingPlayer;

	public Behavior watchingDirection;
	public Behavior movingToGoal;
	public Behavior moveWhenPushed;

	private void addLivingBehaviors() {

		watchingDirection = new WatchingDirection(this);
		addBehavior(watchingDirection);

		movingToGoal = new MovingToGoal(this);
		addBehavior(movingToGoal);

		moveWhenPushed = new MoveWhenPushed(this);
		addBehavior(moveWhenPushed);
	}

	public abstract Behavior[] behaviorToPauseIfStunned();

	////////// RENDER ////////////

	private CONDITION condition;

	@Override
	public CONDITION getCondition() {
		return condition;
	}

	@Override
	public void setCondition(CONDITION condition) {
		if (condition == null)
			condition = CONDITION.GOOD;

		this.condition = condition;
		removeBehavior(livingRender);

		switch (condition) {
		case GOOD:
			livingRender = new WalkingRender(this);
			break;
		case SICK:
			livingRender = new SickRender(this);
			break;
		}

		addBehavior(livingRender);
	}

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
