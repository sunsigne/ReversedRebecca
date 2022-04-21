package com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings;

import java.awt.Color;
import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.Behavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.behaviors.PushingPlayer;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.characteristics.PlayerAvoider;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.behaviors.MoveWhenPushed;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.behaviors.StopWhenMeetPlayer;
import com.sunsigne.reversedrebecca.object.piranha.characteristics.Feeling;
import com.sunsigne.reversedrebecca.object.piranha.characteristics.SpeedVariator;

public abstract class LivingObject extends ExtraBehaviorsObject
		implements CollisionDetector, PlayerAvoider, SpeedVariator {

	public LivingObject(String name, int x, int y, AVOIDERTYPE playerAvoiderType) {
		super(name, x, y);
		setSpeedness(SPEEDNESS.NORMAL);
		setPlayerAvoiderType(playerAvoiderType);
		addLivingBehaviors();
	}

	////////// NAME ////////////

	@Override
	public void setName(String name) {
		super.setName(name);
	}

	////////// SPEEDNESS ////////////

	private SPEEDNESS speedness;

	@Override
	public SPEEDNESS getSpeedness() {
		return speedness;
	}

	@Override
	public void setSpeedness(SPEEDNESS speedness) {
		this.speedness = speedness;
	}

	////////// BEHAVIOR ////////////

	public Behavior avoidingPlayer;

	public Behavior moveWhenPushed;

	private void addLivingBehaviors() {

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
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(getX(), getY(), getWidth(), getHeight());
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
