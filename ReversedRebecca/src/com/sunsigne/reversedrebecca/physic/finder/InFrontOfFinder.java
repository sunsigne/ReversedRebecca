package com.sunsigne.reversedrebecca.physic.finder;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Facing;

public class InFrontOfFinder {

	public int[] getPos(GameObject object) {
		if (object instanceof Facing == false)
			return null;

		int[] pos = new int[2];
		Facing facing = (Facing) object;

		switch (facing.getFacing()) {

		case LEFT:
			pos[0] = object.getX() - object.getWidth();
			pos[1] = object.getY();
			break;
		case RIGHT:
			pos[0] = object.getX() + object.getWidth();
			pos[1] = object.getY();
			break;
		case UP:
			pos[0] = object.getX();
			pos[1] = object.getY() - object.getHeight();
			break;
		case DOWN:
			pos[0] = object.getX();
			pos[1] = object.getY() + object.getHeight();
			break;
		case NULL:
			return null;
		}

		return pos;
	}
	
}
