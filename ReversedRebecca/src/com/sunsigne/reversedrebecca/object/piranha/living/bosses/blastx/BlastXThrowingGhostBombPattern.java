package com.sunsigne.reversedrebecca.object.piranha.living.bosses.blastx;

import com.sunsigne.reversedrebecca.object.hostile.GhostBomb;
import com.sunsigne.reversedrebecca.object.piranha.living.bosses.BossObject;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.RandomGenerator;

public class BlastXThrowingGhostBombPattern extends BlastXThrowingBombPattern {

	public BlastXThrowingGhostBombPattern(BossObject boss) {
		super(boss, 8, 120);
	}

	////////// TICK ////////////

	@Override
	protected void attack(Player player) {
		var rad = new RandomGenerator();

		for (int i = 0; i < 20; i++) {
			var bomb = new GhostBomb(getBoss().getX(), getBoss().getY());
			int velX;
			int velY;
			
			do {
				velX = rad.getIntBetween(-28, 28);
				velY = rad.getIntBetween(-28, 28);
			}
			while(Math.abs(velX) + Math.abs(velY) < 15);
			
			bomb.setVelX(velX);
			bomb.setVelY(velY);
			player.getHandler().addObject(bomb);
		}
	}

}
