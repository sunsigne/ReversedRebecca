package com.sunsigne.reversedrebecca.object.piranha;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.system.mainloop.RenderFree;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;

public class InteractiveObject extends PiranhaObject implements TickFree, RenderFree {

	public InteractiveObject(String name, int x, int y) {
		super(name, x, y);
		setStunned(true); // looks stupid, but allow to bypass the RoundToTileLaw
	}

	////////// PATH FINDER ////////////

	@Override
	public boolean mustFollowPath() {
		return false;
	}

	////////// COLLISION ////////////

	@Override
	public void collidingReaction(CollisionDetector detectorObject) {
		blockPath(detectorObject);
	}

}
