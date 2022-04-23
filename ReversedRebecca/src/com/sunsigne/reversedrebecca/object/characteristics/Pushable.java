package com.sunsigne.reversedrebecca.object.characteristics;

public interface Pushable extends CollisionDetector, Stunnable, SpeedVariator {

	default void setStunned(boolean stunned) {
		setMotionless();
	}
	
}
