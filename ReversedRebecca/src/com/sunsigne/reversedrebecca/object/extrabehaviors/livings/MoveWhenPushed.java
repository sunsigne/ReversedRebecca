package com.sunsigne.reversedrebecca.object.extrabehaviors.livings;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.extrabehaviors.Behavior;

public class MoveWhenPushed implements Behavior {

	public int speed = 20;

	public MoveWhenPushed(LivingObject living) {
		this.living = living;
	}

	////////// BEHAVIOR ////////////

	private LivingObject living;

	@Override
	public LivingObject getExtraBehaviorsObject() {
		return living;
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

	////////// PUSHING ////////////

	private boolean isPushed;

	public boolean isPushed() {
		return isPushed;
	}

	public void setPushed(boolean isPushed) {
		this.isPushed = isPushed;
	}		
	
	public void pushToward(DIRECTION facing) {
		paralyse();
		pushed(facing);
		setPushed(true);
	}

	private void paralyse() {
		if (isPushed())
			living.setMotionless();
	}

	private void pushed(DIRECTION facing) {
		if (isPushed())
			return;		
		if (facing == DIRECTION.LEFT)
			living.setSurVelX(-speed);
		if (facing == DIRECTION.RIGHT)
			living.setSurVelX(speed);
		if (facing == DIRECTION.UP)
			living.setSurVelY(-speed);
		if (facing == DIRECTION.DOWN)
			living.setSurVelY(speed);
	}

	////////// TICK ////////////

	private final int PUSHING_TIME = 10;
	private int time = PUSHING_TIME;

	@Override
	public void tick() {
		if (isPushed()) time--;
		if (time < 0) stabilize();
	}

	private void stabilize() {
		time = PUSHING_TIME;
		setPushed(false);
		living.setMotionless();
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {

	}
	
	//////////COLLISION ////////////
	
	@Override
	public void collidingReaction(CollisionDetector detectorObject) {

	}

}