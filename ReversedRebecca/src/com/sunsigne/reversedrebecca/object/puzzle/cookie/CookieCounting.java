package com.sunsigne.reversedrebecca.object.puzzle.cookie;

import com.sunsigne.reversedrebecca.object.characteristics.Velocity;

public interface CookieCounting extends Velocity {

	float getCount();

	void addToCount(float amount);

}
