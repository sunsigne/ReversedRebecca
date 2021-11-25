package com.sunsigne.reversedrebecca.object.extrabehaviors.livings;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.extrabehaviors.Behavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player.Player;

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
		removeControls();
		pushed(facing);
		setPushed(true);
	}

	private void paralyse() {
		if (isPushed())
			living.setMotionless();
	}

	private void removeControls() {
		living.removeBehavior(living.watchingDirection);
		if(living instanceof Player)
		living.removeBehavior(((Player) living).userCanKeyMove);
	}

	private void pushed(DIRECTION facing) {
		if (isPushed())
			return;		
		if (facing == DIRECTION.LEFT)
			living.setVelX(-speed);
		if (facing == DIRECTION.RIGHT)
			living.setVelX(speed);
		if (facing == DIRECTION.UP)
			living.setVelY(-speed);
		if (facing == DIRECTION.DOWN)
			living.setVelY(speed);
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
		living.addBehavior(living.watchingDirection);
		if(living instanceof Player)
		living.addBehavior(((Player) living).userCanKeyMove);
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {

	}

	////////// KEYBOARD ////////////

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}