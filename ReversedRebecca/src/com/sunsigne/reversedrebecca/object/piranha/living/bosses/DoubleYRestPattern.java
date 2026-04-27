package com.sunsigne.reversedrebecca.object.piranha.living.bosses;

import com.sunsigne.reversedrebecca.object.piranha.living.bosses.doubley.DoubleYFeeling;
import com.sunsigne.reversedrebecca.object.piranha.living.bosses.doubley.DoubleYFeeling.DOUBLE_Y_CONDITION;
import com.sunsigne.reversedrebecca.pattern.GameTimer;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.piranha.condition.global.BossCondition;
import com.sunsigne.reversedrebecca.system.mainloop.Game;

public class DoubleYRestPattern extends BossRestPattern {

	public DoubleYRestPattern(BossObject boss) {
		super(boss, 4, -1);

		GenericListener listener = getActionWhenFinished();
		setActionWhenFinished(() -> {
			listener.doAction();

			int x = getBoss().getX();
			int y = getBoss().getY();
			getBoss().setX(-100);
			getBoss().setY(-100);

			GenericListener tp = () -> {
				getBoss().setX(x);
				getBoss().setY(y);
			};
			
			new GameTimer(3 * Game.SEC, tp);
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
		((DoubleYFeeling) getBoss()).setDoubleYCondition(DOUBLE_Y_CONDITION.TIRED);
		new BossCondition().registerValue("REST");

	}

}
