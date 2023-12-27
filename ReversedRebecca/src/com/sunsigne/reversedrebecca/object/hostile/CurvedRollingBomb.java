package com.sunsigne.reversedrebecca.object.hostile;

import com.sunsigne.reversedrebecca.pattern.RandomGenerator;

public class CurvedRollingBomb extends RollingBomb {

	public CurvedRollingBomb(int x, int y) {
		super(x, y);
	}

	////////// MOUVEMENT ////////////

	private boolean horizontal;
	private int registeredDirection;

	@Override
	public void movingtoPlayer() {
		super.movingtoPlayer();

		horizontal = new RandomGenerator().getBoolean();

		if (horizontal) {
			registeredDirection = Integer.signum(getVelY());
			setVelY(0);
		} else {
			registeredDirection = Integer.signum(getVelX());
			setVelX(0);
		}
	}

	////////// TICK ////////////

	private int curving;

	@Override
	public void tick() {
		super.tick();
		curving++;

		if (horizontal)
			setVelY(curving * registeredDirection);
		else
			setVelX(curving * registeredDirection);
	}

	public int getExplodingTime() {
		return 30;
	}
	
}
