package com.sunsigne.reversedrebecca.object.hostile;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.system.Size;

public class FallingBomb extends ExplodingBomb implements CollisionReactor {

	public FallingBomb(int x, int y) {
		super(x, y, Size.XL / 2, Size.XL / 2);
	}

	////////// TICK ////////////

	private int time;

	@Override
	public void tick() {
		time++;

		if (time == getExplodingTime())
			canHurtPlayer = true;

		if (time > getExplodingTime())
			explode();
	}

	@Override
	public int getExplodingTime() {
		return 25;
	}
	
	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		int size = 3 * (getExplodingTime() - time);
		
		int x = getX() - size / 2;
		int y = getY() - size / 2;
		int w = getWidth() + size;
		int h = getHeight() + size;
		
		g.drawImage(getImage(), x, y, w, h, null);
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
