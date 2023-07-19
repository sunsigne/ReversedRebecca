package com.sunsigne.reversedrebecca.object.piranha.living.bosses;

import com.sunsigne.reversedrebecca.object.piranha.living.characteristics.Feeling.CONDITION;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.piranha.condition.global.BossCondition;

public class BossRestPattern extends BossPattern {

	public BossRestPattern(BossObject boss) {
		super(boss, 8);

		GenericListener listener = getActionWhenFinished();
		setActionWhenFinished(() -> {
			getBoss().setCondition(CONDITION.GOOD);
			new BossCondition().registerValue("READY");
			getBoss().evolve();
			getBoss().patterns = null;
			listener.doAction();
		});
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
		new BossCondition().registerValue("REST");
	}

}
