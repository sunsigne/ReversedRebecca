package com.sunsigne.reversedrebecca.world.behaviors;

import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public interface WorldRenderBehavior  extends WorldBehavior, Updatable {

	default void tick() {
		
	}

}
