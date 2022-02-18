package com.sunsigne.reversedrebecca.object.extrabehaviors.livings.behaviors;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.characteristics.PathSearcher;
import com.sunsigne.reversedrebecca.object.characteristics.Position;
import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.TickBehavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.LivingObject;

public class MovingToGoal implements PathSearcher, TickBehavior {

	public MovingToGoal(LivingObject living) {
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

	////////// VELOCITY ////////////

	@Override
	public int getVelX() {
		return getExtraBehaviorsObject().getVelX();
	}

	@Override
	public int getVelY() {
		return getExtraBehaviorsObject().getVelY();
	}

	@Override
	public void setVelX(int velX) {
		getExtraBehaviorsObject().setVelX(velX);
	}

	@Override
	public void setVelY(int velY) {
		getExtraBehaviorsObject().setVelY(velY);
	}

	////////// PATH FINDER ////////////s

	@Override
	public void setGoal(Position goal) {
		getExtraBehaviorsObject().setGoal(goal);
	}

	@Override
	public Position getGoal() {
		return getExtraBehaviorsObject().getGoal();
	}

	@Override
	public void setPath(DIRECTION path) {
		getExtraBehaviorsObject().setPath(path);
	}

	@Override
	public DIRECTION getPath() {
		return getExtraBehaviorsObject().getPath();
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		if (getPath() == null)
			return;

		setMotionless();
		followPath();

	}

	private void followPath() {

		switch (getPath()) {
		case NULL:
			setMotionless();
			break;
		case LEFT:
			setVelX(-living.speed);
			break;
		case RIGHT:
			setVelX(living.speed);
			break;
		case UP:
			setVelY(-living.speed);
			break;
		case DOWN:
			setVelY(living.speed);
			break;
		}
	}

}