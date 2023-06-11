package com.sunsigne.reversedrebecca.object.characteristics;

import com.sunsigne.reversedrebecca.pattern.cycloid.Cycloid;
import com.sunsigne.reversedrebecca.world.World;

public interface Blinking extends Velocity {

	////////// BLINKING ////////////
	
	final int BLINKING_TOTAL_TIME = 100;
	final Cycloid<Boolean> blinking = new Cycloid<Boolean>(false, true);
	
	int getBlinkingTime();
	
	void setBlinkingTime(int time);
	
	default boolean isBlinking() {
		return blinking.getState();
	}

	default void setBlinking() {
		World world = World.get();
		if (world != null && world.getTime() < 1)
			return;

		setBlinkingTime(BLINKING_TOTAL_TIME);
		blinking.setState(true);
	}

	////////// TICK ////////////

	default void runBlinking() {
		if (getBlinkingTime() < 0)
			return;

		setBlinkingTime(getBlinkingTime() - 1); // -> time --
		if (getBlinkingTime() % 10 == 0)
			blinking.cycle();
		if (getBlinkingTime() == 0)
			blinking.setState(false);
	}
	
}
