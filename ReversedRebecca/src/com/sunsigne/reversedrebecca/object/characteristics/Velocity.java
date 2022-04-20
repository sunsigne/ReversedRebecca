package com.sunsigne.reversedrebecca.object.characteristics;

import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public interface Velocity extends Position, Updatable {

	////////// VELOCICY ////////////

	default int getSpeed() {
		return getSize() / 16;
	}
	
	int getVelX();

	int getVelY();

	void setVelX(int velX);

	void setVelY(int velY);

	default boolean isMotionlessbyX() {
		return getVelX() == 0;
	}

	default boolean isMotionlessbyY() {
		return getVelY() == 0;
	}

	default boolean isMotionless() {
		return isMotionlessbyX() && isMotionlessbyY();
	}

	default void setMotionless() {
		setVelX(0);
		setVelY(0);
	}

}
