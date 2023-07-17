package com.sunsigne.reversedrebecca.object.piranha.living.bosses;

import com.sunsigne.reversedrebecca.object.piranha.living.characteristics.Feeling.CONDITION;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;

public class BossRestPattern extends BossPattern {

	public BossRestPattern(BossObject boss) {
		super(boss);
	}

	////////// PATTERN ////////////

	@Override
	public int getPatternTimeInSec() {
		// return 8;
		return 3;
	}

	@Override
	public GenericListener getActionWhenFinished() {
		GenericListener listener = () -> {
			getBoss().setCondition(CONDITION.GOOD);
			getBoss().patterns = null;
		};
		return listener;
	}

	////////// TICK ////////////

	private boolean flag;

	@Override
	public void tick() {
		super.tick();

		if (flag)
			return;

		flag = true;
		getBoss().setMotionless();
		getBoss().setCondition(CONDITION.KO);
	}

}
