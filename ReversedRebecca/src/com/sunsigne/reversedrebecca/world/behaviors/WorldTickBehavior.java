package com.sunsigne.reversedrebecca.world.behaviors;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public interface WorldTickBehavior  extends WorldBehavior, Updatable {

	default void render(Graphics g) {
		
	}

}
