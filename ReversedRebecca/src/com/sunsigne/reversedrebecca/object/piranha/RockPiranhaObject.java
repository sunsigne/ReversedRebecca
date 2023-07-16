package com.sunsigne.reversedrebecca.object.piranha;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.system.mainloop.RenderFree;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;

public class RockPiranhaObject extends PiranhaObject implements TickFree, RenderFree {

	public RockPiranhaObject(int x, int y) {
		super("rock", x, y);
		setStunned(true); // looks stupid, but allow to bypass the RoundToTileLaw
	}

	@Override
	public void collidingReaction(CollisionDetector detectorObject) {
		blockPath(detectorObject);
	}

}
