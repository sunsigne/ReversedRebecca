package com.sunsigne.reversedrebecca.object.piranha;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;

public abstract class LivingObject extends PiranhaObject implements CollisionDetector {

	public LivingObject(String name, int x, int y) {
		super(name, x, y);
	}

}
