package com.sunsigne.reversedrebecca.object.piranha.living.bosses.blastx;

import com.sunsigne.reversedrebecca.object.hostile.RollingBomb;
import com.sunsigne.reversedrebecca.object.piranha.living.bosses.BossObject;
import com.sunsigne.reversedrebecca.object.piranha.living.bosses.BossPattern;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;

public class BlastXThrowingBombPattern extends BossPattern {

	protected BlastXThrowingBombPattern(BossObject boss, int pattern_time_in_sec, int delay_between_two_attacks) {
		super(boss, pattern_time_in_sec, delay_between_two_attacks);
	}
	
	public BlastXThrowingBombPattern(BossObject boss) {
		this(boss, 8, 40);
	}

	////////// TICK ////////////

	private int time;

	@Override
	public void tick() {
		super.tick();

		Player player = new PlayerFinder().getPlayer();
		if (player == null)
			return;

		time++;
		if (time < getDelayBetweenTwoAttacks())
			return;

		time = 0;
		attack(player);
	}

	protected void attack(Player player) {
		var bomb = new RollingBomb(getBoss().getX(), getBoss().getY());
		bomb.movingtoPlayer();
		player.getHandler().addObject(bomb);
	}

}
