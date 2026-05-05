package com.sunsigne.reversedrebecca.object.piranha.living.bosses.doubley;

import com.sunsigne.reversedrebecca.object.piranha.living.bosses.BossObject;
import com.sunsigne.reversedrebecca.object.piranha.living.bosses.doubley.DoubleYFeeling.DOUBLE_Y_CONDITION;
import com.sunsigne.reversedrebecca.object.piranha.living.characteristics.Feeling.CONDITION;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.GameTimer;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.system.mainloop.Game;

public class DoubleUppercutPattern extends DoubleYSSJ2UppercutPattern {

	protected DoubleUppercutPattern(BossObject boss, int pattern_time_in_sec, int delay_between_two_attacks) {
		super(boss, pattern_time_in_sec, delay_between_two_attacks);
		time = ((3 * Game.SEC) / 2) + 10;

		setActionWhenFinished(() -> {
			getBoss().nextPattern();
			getBoss().setDoubleYCondition(DOUBLE_Y_CONDITION.PUSH_UP);
		});
	}

	public DoubleUppercutPattern(BossObject boss) {
		this(boss, 24, 7 * Game.SEC);
	}

	////////// TICK ////////////

	private boolean flag;

	@Override
	public void tick() {
		super.tick();

		if (flag)
			return;

		flag = true;
		getBoss().setDoubleYCondition(DOUBLE_Y_CONDITION.PUSH_UP);

		Player player = new PlayerFinder().getPlayer();
		if (player == null)
			return;

		player.setCondition(CONDITION.KO_UPSIDEDOWN);
		player.setStunned(true);
		new GameTimer(3 * Game.SEC, () -> {
			player.setCondition(CONDITION.GOOD);
			player.setStunned(false);
		});
	}

}
