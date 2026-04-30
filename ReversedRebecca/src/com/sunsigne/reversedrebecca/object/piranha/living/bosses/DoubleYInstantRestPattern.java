package com.sunsigne.reversedrebecca.object.piranha.living.bosses;

public class DoubleYInstantRestPattern extends DoubleYRestPattern {

	public DoubleYInstantRestPattern(BossObject boss) {
		super(boss);

		setActionWhenFinished(() -> {
			getBoss().evolve();
			getBoss().patterns = null;
		});
	}

	////////// PATTERN ////////////

	@Override
	public int getPatternTimeInSec() {
		return 0;
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		removeObject();

		if (getActionWhenFinished() != null)
			getActionWhenFinished().doAction();

	}

}
