package com.sunsigne.reversedrebecca.object.piranha.living.bosses;

import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.system.mainloop.Game;
import com.sunsigne.reversedrebecca.system.mainloop.RenderFree;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public abstract class BossPattern implements Updatable, RenderFree {

	public BossPattern(BossObject boss, int pattern_time_in_sec, int delay_between_two_attacks) {
		this.boss = boss;
		this.pattern_time_in_sec = pattern_time_in_sec;
		this.delay_between_two_attacks = delay_between_two_attacks;

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

	private int delay_between_two_attacks;

	public int getDelayBetweenTwoAttacks() {
		return delay_between_two_attacks;
	}

	private GenericListener actionWhenFinished;

	public void setActionWhenFinished(GenericListener actionWhenFinished) {
		this.actionWhenFinished = actionWhenFinished;
	}

	public GenericListener getActionWhenFinished() {
		return actionWhenFinished;
	}

	////////// TICK ////////////

	public void interrupt() {
		time = 999;
	}

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
