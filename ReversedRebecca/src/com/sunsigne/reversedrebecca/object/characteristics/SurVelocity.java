package com.sunsigne.reversedrebecca.object.characteristics;

public interface SurVelocity extends Velocity {

	////////// SURVELOCICY ////////////

	int getSurVelX();

	int getSurVelY();

	void setSurVelX(int velX);

	void setSurVelY(int velY);

	@Override
	default boolean isMotionlessbyX() {
		return getVelX() == 0 && getSurVelX() == 0;
	}

	@Override
	default boolean isMotionlessbyY() {
		return getVelY() == 0 && getSurVelY() == 0;
	}
	
	default boolean isSurMotionlessbyX() {
		return getSurVelX() == 0;
	}

	default boolean isSurMotionlessbyY() {
		return getSurVelY() == 0;
	}
	
	default boolean isSurMotionless() {
		return isSurMotionlessbyX() && isSurMotionlessbyY();
	}

	@Override
	default void setMotionless() {
		setVelX(0);
		setVelY(0);
		setSurVelX(0);
		setSurVelY(0);
	}

}
