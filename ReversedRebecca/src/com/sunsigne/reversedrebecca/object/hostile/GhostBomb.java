package com.sunsigne.reversedrebecca.object.hostile;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;

public class GhostBomb extends ExplodingBomb implements CollisionReactor {

	public GhostBomb(int x, int y) {
		super(x, y);
	}

	public GhostBomb(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	////////// MOUVEMENT ////////////

	public int getSpeed() {
		return 2;
	}

	////////// TICK ////////////

	private int time;

	@Override
	public void tick() {
		time++;

		reduceSpeed();
		
		if (time == getExplodingTime())
			canHurtPlayer = true;

		if (time > getExplodingTime())
			explode();
	}
	
	private void reduceSpeed() {
		double reducer = Math.pow((double) time, 0.01d);
		
		double velX = (double) getVelX() / reducer;
		double velY = (double) getVelY() / reducer;
		
		setVelX((int) velX);
		setVelY((int) velY);
	}

	@Override
	public int getExplodingTime() {
		return 55;
	}

	////////// COLLISION ////////////

	private boolean canHurtPlayer;

	@Override
	public void collidingReaction(CollisionDetector detectorObject) {
		if (detectorObject instanceof Player == false)
			return;

		collidingReaction(detectorObject, false, () -> {
			if (canHurtPlayer)
				removeHp();
		});
	}

}
