package com.sunsigne.reversedrebecca.object.piranha.living.bosses;

import com.sunsigne.reversedrebecca.object.piranha.living.bosses.doubley.DoubleYFeeling;
import com.sunsigne.reversedrebecca.object.piranha.living.bosses.doubley.DoubleYFeeling.DOUBLE_Y_CONDITION;
import com.sunsigne.reversedrebecca.piranha.condition.global.BossCondition;

public class DoubleYRestPattern extends BossRestPattern {

	public DoubleYRestPattern(BossObject boss) {
		super(boss, 4, -1);
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
		((DoubleYFeeling) getBoss()).setDoubleYCondition(DOUBLE_Y_CONDITION.TIRED);
		new BossCondition().registerValue("REST");
	}

}
