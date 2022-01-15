package com.sunsigne.reversedrebecca.object.extrabehaviors;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;
import com.sunsigne.reversedrebecca.object.characteristics.Facing;
import com.sunsigne.reversedrebecca.object.characteristics.SurVelocity;
import com.sunsigne.reversedrebecca.pattern.list.GameLimitedList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;

public abstract class ExtraBehaviorsObject extends GameObject implements Behavior, CollisionReactor, SurVelocity, Facing  {

	public ExtraBehaviorsObject(String name, int x, int y) {
		super(x, y);
		this.name = name.toLowerCase();
	}

	////////// MAP OR LIST ////////////
	
	private GameLimitedList<Behavior> list = new GameLimitedList<>(LISTTYPE.ARRAY);

	public GameLimitedList<Behavior> getBehaviorList() {
		return list;
	}
	
	public void addBehavior(Behavior behavior) {
		getBehaviorList().addObject(behavior);
	}

	public void removeBehavior(Behavior behavior) {
		getBehaviorList().removeObject(behavior);
	}
	
	////////// BEHAVIOR ////////////
	
	private String name;
	
	@Override
	public ExtraBehaviorsObject getExtraBehaviorsObject() {
		return this;
	}
	
	public String getName() {
		return name;
	}
	
	////////// SURVELOCICY ////////////

	private int surVelX, surVelY;

	@Override
	public int getSurVelX() {
		return surVelX;
	}

	@Override
	public int getSurVelY() {
		return surVelY;
	}

	@Override
	public void setSurVelX(int surVelX) {
		this.surVelX = surVelX;
	}

	@Override
	public void setSurVelY(int surVelY) {
		this.surVelY = surVelY;
	}
	
	////////// FACING ////////////
	
	private DIRECTION facing = DIRECTION.DOWN;
	
	@Override
	public DIRECTION getFacing() {
		return facing;
	}
	
	@Override
	public void setFacing(DIRECTION facing) {
		this.facing = facing;
	}	

	////////// TICK ////////////

	@Override
	public void tick() {
		for (Behavior tempBehavior : getBehaviorList().getList()) {
			if (tempBehavior != null)
				tempBehavior.tick();
		}
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		for (Behavior tempBehavior : getBehaviorList().getList()) {
			if (tempBehavior != null)
				tempBehavior.render(g);
		}
	}
	
	////////// COLLISION ////////////
	
	@Override
	public void collidingReaction(CollisionDetector detectorObject) {
		for (Behavior tempBehavior : getBehaviorList().getList()) {
			if (tempBehavior != null)
				tempBehavior.collidingReaction(detectorObject);
		}
	}

}
