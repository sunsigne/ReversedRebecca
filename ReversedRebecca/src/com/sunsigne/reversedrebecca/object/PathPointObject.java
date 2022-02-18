package com.sunsigne.reversedrebecca.object;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.characteristics.PathSearcher;
import com.sunsigne.reversedrebecca.object.characteristics.Position;

public class PathPointObject extends GameObject implements PathSearcher {

	public PathPointObject(int x, int y) {
		super(x, y);

	}

	////////// PATH FINDER ////////////

	private Position goal;

	@Override
	public Position getGoal() {
		return goal;
	}

	@Override
	public void setGoal(Position goal) {
		this.goal = goal;
	}

	private DIRECTION path;

	@Override
	public DIRECTION getPath() {
		return path;
	}

	@Override
	public void setPath(DIRECTION path) {
		this.path = path;
	}

	////////// TICK ////////////

	@Override
	public void tick() {

	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {

	}

}
