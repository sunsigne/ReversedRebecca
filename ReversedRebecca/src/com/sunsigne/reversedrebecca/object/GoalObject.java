package com.sunsigne.reversedrebecca.object;

import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.mainloop.RenderFree;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;

public class GoalObject extends GameObject implements TickFree, RenderFree {

	public GoalObject(int x, int y, boolean reversed) {
		super(reversed ? x / Size.M : x * Size.M, reversed ? y / Size.M : y * Size.M);
	}
	
	////////// NAME ////////////

	@Override
	public String toString() {
		var clazz = "GOAL";
		return clazz + " : " + getX() / Size.M + "-" + getY() / Size.M;
	}

}
