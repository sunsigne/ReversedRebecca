package com.sunsigne.reversedrebecca.object.piranha;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.system.mainloop.RenderFree;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;

public class RockPiranhaObject extends PiranhaObject implements TickFree, RenderFree {

	public RockPiranhaObject(int x, int y, int hitboxX, int hitboxY, int type) {
		super("rock", x, y);
		setStunned(true); // looks stupid, but allow to bypass the RoundToTileLaw

		carvingHitbox(hitboxX, hitboxY, type);
	}

	@Override
	public void collidingReaction(CollisionDetector detectorObject) {
		blockPath(detectorObject);
	}

	private void carvingHitbox(int hitboxX, int hitboxY, int type) {
		int[] rect = getRectFromType(type);
		setBounds(hitboxX + rect[0], hitboxY + rect[1], rect[2], rect[3]);
	}

	private int[] getRectFromType(int type) {
		switch (type) {
		case 1:
			return new int[] { 0, -2, 8, 11 }; // pyramidal rock
		case 2:
			return new int[] { 0, -2, 10, 10 }; // small rock	
		case 3:
			return new int[] { 0, -2, 13, 11 }; // flat rock		
		case 4:
			return new int[] { 0, -2, 12, 16 }; // big rock
		default:
			return new int[] { 0, 0, 16, 16 };
		}
	}

}
