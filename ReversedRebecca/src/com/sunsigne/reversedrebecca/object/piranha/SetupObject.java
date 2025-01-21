package com.sunsigne.reversedrebecca.object.piranha;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.mainloop.RenderFree;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;

public class SetupObject extends PiranhaObject implements TickFree, RenderFree {

	public SetupObject() {
		super("set-up", -Size.M, -Size.M);
		setDisabled(true);
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		TickFree.super.tick();
	}
	
	////////// PATH FINDER ////////////

	@Override
	public boolean mustFollowPath() {
		return false;
	}

	////////// COLLISION ////////////

	@Override
	public boolean isBlockingPath() {
		return false;
	}

	@Override
	public void collidingReaction(CollisionDetector detectorObject) {

	}

}
