package com.sunsigne.reversedrebecca.object.extrabehaviors;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Facing;
import com.sunsigne.reversedrebecca.object.characteristics.PathSearcher;
import com.sunsigne.reversedrebecca.object.characteristics.Position;
import com.sunsigne.reversedrebecca.object.characteristics.SurVelocity;
import com.sunsigne.reversedrebecca.system.Size;

public abstract class SuperExtraBehaviorsObject extends GameObject implements SurVelocity, Facing, PathSearcher {

	public SuperExtraBehaviorsObject(int x, int y) {
		this(x, y, Size.M, Size.M);
	}

	public SuperExtraBehaviorsObject(int x, int y, int w, int h) {
		super(x, y, w, h);
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

	////////// PATH FINDER ////////////

	private Position goal;

	@Override
	public void setGoal(Position goal) {
		this.goal = goal;
	}

	@Override
	public Position getGoal() {
		return goal;
	}

	private DIRECTION path;

	@Override
	public void setPath(DIRECTION path) {
		this.path = path;
	}

	@Override
	public DIRECTION getPath() {
		return path;
	}

	////////// RENDER ////////////

	private boolean invisible;

	public boolean isInvisible() {
		return invisible;
	}

	public void setInvisible(boolean invisible) {
		this.invisible = invisible;
	}

}
