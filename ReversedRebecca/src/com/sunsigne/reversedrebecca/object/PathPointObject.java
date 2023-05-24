package com.sunsigne.reversedrebecca.object;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.characteristics.PathSearcher;
import com.sunsigne.reversedrebecca.object.characteristics.Position;
import com.sunsigne.reversedrebecca.system.mainloop.RenderFree;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;

public class PathPointObject extends GameObject implements TickFree, RenderFree, PathSearcher {

	public PathPointObject(int x, int y) {
		super(x, y);
	}

	////////// NAME ////////////
	
	@Override
	public String toString() {
		var clazz = "PATHPOINT";
		var goal = new GoalObject(getX(), getY(), true);
		return clazz + " : " + goal.getX() + "-" + goal.getY();
	}
		
	////////// PATH FINDER ////////////

	@Override
	public boolean mustFollowPath() {
		return false;
	}

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

	@Override
	public boolean isPathFinderDisabled() {
		return false;
	}

	@Override
	public void enablePathFinder() {

	}

	@Override
	public void disabledPathFinder() {

	}

}
