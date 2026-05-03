package com.sunsigne.reversedrebecca.object.piranha.living.bosses.doubley;

import com.sunsigne.reversedrebecca.object.piranha.living.bosses.BossObject;
import com.sunsigne.reversedrebecca.object.piranha.living.bosses.doubley.DoubleYFeeling.DOUBLE_Y_CONDITION;

public class DoubleYPosePattern extends DoubleYMovePattern {

	protected DoubleYPosePattern(BossObject boss, int pattern_time_in_sec, int delay_between_two_attacks,
			DOUBLE_Y_CONDITION doubleYCondition, int x0, int y0) {
		super(boss, pattern_time_in_sec, delay_between_two_attacks);
		
		this.doubleYCondition = doubleYCondition;
		this.x0 = x0;
		this.y0 = y0;
		
		setActionWhenFinished(() -> {
			getBoss().nextPattern();
			getBoss().setDoubleYCondition(doubleYCondition);
		});
	}

	private DOUBLE_Y_CONDITION doubleYCondition;
	private int x0, y0;

	////////// TICK ////////////

	private int time;

	@Override
	public void tick() {
		super.tick();
		time++;

		if (time > getDelayBetweenTwoAttacks() && time < 2 * getDelayBetweenTwoAttacks()) {
			movingToGoal(x0, y0);
			return;
		}

		if (time == 2 * getDelayBetweenTwoAttacks())
			startActing(doubleYCondition);
	}

}
