package com.sunsigne.reversedrebecca.object.piranha.living.bosses.blastx;

import com.sunsigne.reversedrebecca.object.hostile.FallingBomb;
import com.sunsigne.reversedrebecca.object.piranha.living.bosses.BossObject;
import com.sunsigne.reversedrebecca.object.piranha.living.bosses.BossPattern;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;

public class BlastXBombFromSkyPattern extends BossPattern {

	public BlastXBombFromSkyPattern(BossObject boss, int delay_between_two_attacks) {
		super(boss, 8);
		this.delay_between_two_attacks = delay_between_two_attacks;
	}

	////////// TICK ////////////

	private int delay_between_two_attacks;
	private int time;

	@Override
	public void tick() {
		super.tick();

		Player player = new PlayerFinder().getPlayer();
		if (player == null)
			return;

		time++;
		if (time < delay_between_two_attacks)
			return;

		time = 0;
		attack(player);
	}

	private void attack(Player player) {
		var bomb = new FallingBomb(player.getX(), player.getY());
		player.getHandler().addObject(bomb);
	}

}