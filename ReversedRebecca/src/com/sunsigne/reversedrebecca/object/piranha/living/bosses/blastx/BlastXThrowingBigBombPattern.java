package com.sunsigne.reversedrebecca.object.piranha.living.bosses.blastx;

import com.sunsigne.reversedrebecca.object.hostile.BigRollingBomb;
import com.sunsigne.reversedrebecca.object.piranha.living.bosses.BossObject;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;

public class BlastXThrowingBigBombPattern extends BlastXThrowingBombPattern {

	public BlastXThrowingBigBombPattern(BossObject boss) {
		super(boss, 10, 120);
	}

	////////// TICK ////////////

	@Override
	protected void attack(Player player) {
		var bomb = new BigRollingBomb(getBoss().getX(), getBoss().getY());
		bomb.movingtoPlayer();
		player.getHandler().addObject(bomb);
	}

}
