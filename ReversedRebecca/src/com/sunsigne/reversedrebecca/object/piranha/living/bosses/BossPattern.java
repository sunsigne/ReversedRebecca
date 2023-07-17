package com.sunsigne.reversedrebecca.object.piranha.living.bosses;

import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.system.mainloop.Game;
import com.sunsigne.reversedrebecca.system.mainloop.RenderFree;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public abstract class BossPattern implements Updatable, RenderFree {

	public BossPattern(BossObject boss) {
		this.boss = boss;
		time = getPatternTimeInSec() * Game.SEC;
	}

	////////// PATTERN ////////////

	private BossObject boss;

	public BossObject getBoss() {
		return boss;
	}

	public abstract int getPatternTimeInSec();

	public GenericListener getActionWhenFinished() {
		return null;
	}

	////////// TICK ////////////

	private int time;

	@Override
	public void tick() {
		time--;
		if (time > 0)
			return;

		getBoss().nextPattern();
		removeObject();

		if (getActionWhenFinished() != null)
			getActionWhenFinished().doAction();
	}

}
