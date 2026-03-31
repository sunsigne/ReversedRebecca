package com.sunsigne.reversedrebecca.object;

import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.mainloop.PhysicFree;
import com.sunsigne.reversedrebecca.system.mainloop.RenderFree;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;

public class GoalObject extends GameObject implements PhysicFree, TickFree, RenderFree {

	public GoalObject(int x, int y, boolean reversed) {
		super(reversed ? x / Size.M : x * Size.M, reversed ? y / Size.M : y * Size.M);
		this.reversed = reversed;
	}

	public boolean doesTriggerGoalCondition() {
		return true;
	}

	////////// NAME ////////////

	private boolean reversed;
	
	@Override
	public String toString() {
		var clazz = "GOAL";
		int div = reversed ? 1 : Size.M;
		return clazz + " : " + getX() / div + "-" + getY() / div;
	}

	////////// NAVMESH ////////////

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if ((o instanceof GoalObject) == false)
			return false;
		GoalObject other = (GoalObject) o;

		return getX() == other.getX() && getY() == other.getY();
	}

	@Override
	public int hashCode() {
		int result = Integer.hashCode(getX());
		result = 31 * result + Integer.hashCode(getY());
		return result;
	}

}
