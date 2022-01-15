package com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public interface TickBehavior extends Behavior, Updatable {

	default void render(Graphics g) {
		
	}
}
