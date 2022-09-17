package com.sunsigne.reversedrebecca.object;

import com.sunsigne.reversedrebecca.object.characteristics.Velocity;
import com.sunsigne.reversedrebecca.system.Size;

public abstract class GameObject implements Velocity {

	public GameObject(int x, int y) {
		this(x, y, Size.M, Size.M);
	}

	public GameObject(int x, int y, int w, int h) {

		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	////////// NAME ////////////

	public String toString() { 
		var clazz = "UNKOWN";
		var goal = new GoalObject(getX(), getY(), true);
		return clazz + " : " + goal.getX() + "-" + goal.getY();
	}
	
	////////// POSITION ////////////

	private int x, y;

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}

	////////// SIZE ////////////

	private int w, h;

	@Override
	public int getWidth() {
		return w;
	}

	@Override
	public int getHeight() {
		return h;
	}

	////////// VELOCICY ////////////

	private int velX, velY;

	@Override
	public int getVelX() {
		return velX;
	}

	@Override
	public int getVelY() {
		return velY;
	}

	@Override
	public void setVelX(int velX) {
		this.velX = velX;
	}

	@Override
	public void setVelY(int velY) {
		this.velY = velY;
	}
	
}
