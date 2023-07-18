package com.sunsigne.reversedrebecca.object.piranha.living.bosses;

import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.system.mainloop.Game;
import com.sunsigne.reversedrebecca.system.mainloop.RenderFree;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public abstract class BossPattern implements Updatable, RenderFree {

	public BossPattern(BossObject boss, int pattern_time_in_sec) {
		this.boss = boss;
		this.pattern_time_in_sec = pattern_time_in_sec;

		setActionWhenFinished(() -> getBoss().nextPattern());
	}

	////////// PATTERN ////////////

	private BossObject boss;

	public BossObject getBoss() {
		return boss;
	}

	private int pattern_time_in_sec;

	public void setPatternTimeInSec(int pattern_time_in_sec) {
		this.pattern_time_in_sec = pattern_time_in_sec;
	}

	public int getPatternTimeInSec() {
		return pattern_time_in_sec;
	}

	private GenericListener actionWhenFinished;

	public void setActionWhenFinished(GenericListener actionWhenFinished) {
		this.actionWhenFinished = actionWhenFinished;
	}

	public GenericListener getActionWhenFinished() {
		return actionWhenFinished;
	}

	////////// TICK ////////////

	private int time;

	@Override
	public void tick() {
		time++;
		selfDestructionIfNoBoss();

		if (time < getPatternTimeInSec() * Game.SEC)
			return;

		removeObject();
		
		if (getActionWhenFinished() != null)
			getActionWhenFinished().doAction();
	}

	private void selfDestructionIfNoBoss() {
		if (getBoss().getHandler() == null)
			removeObject();
	}

}
