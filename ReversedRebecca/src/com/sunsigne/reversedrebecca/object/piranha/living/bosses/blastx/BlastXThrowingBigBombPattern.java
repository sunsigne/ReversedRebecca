package com.sunsigne.reversedrebecca.object.piranha.living.bosses.blastx;

import com.sunsigne.reversedrebecca.object.hostile.BigRollingBomb;
import com.sunsigne.reversedrebecca.object.piranha.living.bosses.BossObject;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;

public class BlastXThrowingBigBombPattern extends BlastXThrowingBombPattern {

	public BlastXThrowingBigBombPattern(BossObject boss, int delay_between_two_attacks) {
		super(boss, delay_between_two_attacks);
	}

	////////// PATTERN ////////////

	@Override
	public int getPatternTimeInSec() {
		return 12;
	}

	////////// TICK ////////////

	@Override
	protected void attack(Player player) {
		var bomb = new BigRollingBomb(getBoss().getX(), getBoss().getY());
		bomb.movingtoPlayer();
		player.getHandler().addObject(bomb);
	}

}
