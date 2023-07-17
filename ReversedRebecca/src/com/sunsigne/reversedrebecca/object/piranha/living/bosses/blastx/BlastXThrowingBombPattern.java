package com.sunsigne.reversedrebecca.object.piranha.living.bosses.blastx;

import com.sunsigne.reversedrebecca.object.piranha.living.bosses.BossObject;
import com.sunsigne.reversedrebecca.object.piranha.living.bosses.BossPattern;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.object.puzzler.rubble.ExplodeRubbleAnimationObject;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;

public class BlastXThrowingBombPattern extends BossPattern {

	public BlastXThrowingBombPattern(BossObject boss, int delay_between_two_attacks) {
		super(boss);

		this.delay_between_two_attacks = delay_between_two_attacks;
	}

	////////// PATTERN ////////////

	@Override
	public int getPatternTimeInSec() {
		// return 15;
		return 3;
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
		var object = new ExplodeRubbleAnimationObject(getBoss().getX(), getBoss().getY());
		player.getHandler().addObject(object);
	}

}
