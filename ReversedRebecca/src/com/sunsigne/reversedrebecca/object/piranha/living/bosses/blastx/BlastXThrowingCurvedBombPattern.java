package com.sunsigne.reversedrebecca.object.piranha.living.bosses.blastx;

import com.sunsigne.reversedrebecca.object.hostile.CurvedRollingBomb;
import com.sunsigne.reversedrebecca.object.piranha.living.bosses.BossObject;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;

public class BlastXThrowingCurvedBombPattern extends BlastXThrowingBombPattern {

	public BlastXThrowingCurvedBombPattern(BossObject boss) {
		super(boss);
	}

	////////// TICK ////////////

	@Override
	protected void attack(Player player) {
		var bomb = new CurvedRollingBomb(getBoss().getX(), getBoss().getY());
		bomb.movingtoPlayer();
		player.getHandler().addObject(bomb);
	}

}
