package com.sunsigne.reversedrebecca.object.characteristics;

import com.sunsigne.reversedrebecca.pattern.cycloid.Cycloid;
import com.sunsigne.reversedrebecca.world.World;

public interface Blinking extends Velocity {

	////////// BLINKING ////////////

	default int getTotalBlinkingTime() {
		return 100;
	}

	int getBlinkingTime();

	void setBlinkingTime(int time);

	Cycloid<Boolean> getBlinking();

	default boolean isBlinking() {
		return getBlinking().getState();
	}

	default void setBlinking() {
		World world = World.get();
		if (world != null && world.getTime() < 1)
			return;

		setBlinkingTime(getTotalBlinkingTime());
		getBlinking().setState(true);
	}

	////////// TICK ////////////

	@Override
	default void tick() {
		runBlinking();
	}

	default void runBlinking() {
		if (getBlinkingTime() < 0)
			return;

		setBlinkingTime(getBlinkingTime() - 1); // -> time --
		if (getBlinkingTime() % 10 == 0)
			getBlinking().cycle();
		if (getBlinkingTime() == 0)
			getBlinking().setState(false);
	}

}
