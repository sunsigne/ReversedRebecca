package com.sunsigne.reversedrebecca.object.hostile;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;

public class RollingBomb extends ExplodingBomb implements CollisionReactor {

	public RollingBomb(int x, int y) {
		super(x, y);
	}

	public RollingBomb(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	////////// MOUVEMENT ////////////

	public int getSpeed() {
		return 2;
	}

	////////// TICK ////////////

	@Override
	public int getExplodingTime() {
		return 35;
	}

	////////// COLLISION ////////////

	@Override
	public void collidingReaction(CollisionDetector detectorObject) {
		if (detectorObject instanceof Player == false)
			return;

		collidingReaction(detectorObject, false, () -> {
			removeHp();
			explode();
		});
	}

}
