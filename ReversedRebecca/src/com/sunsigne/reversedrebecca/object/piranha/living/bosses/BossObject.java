package com.sunsigne.reversedrebecca.object.piranha.living.bosses;

import com.sunsigne.reversedrebecca.object.piranha.living.LivingObject;
import com.sunsigne.reversedrebecca.pattern.ArrayCombiner;
import com.sunsigne.reversedrebecca.pattern.GameTimer;
import com.sunsigne.reversedrebecca.pattern.cycloid.Cycloid;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.system.mainloop.Game;

public abstract class BossObject extends LivingObject {

	public BossObject(String name, int x, int y) {
		super(name, x, y);
	}

	////////// USEFUL ////////////

	private void start(BossPattern pattern) {
		var handler = getHandler();
		if (handler != null)
			handler.addObject(pattern);
	}

	////////// STATE ////////////

	private boolean activated;

	public boolean isActivated() {
		return activated;
	}

	public void activate() {
		setCondition(CONDITION.GOOD);
		this.activated = true;
	}

	////////// PATTERN ////////////

	protected Cycloid<BossPattern> patterns;

	public abstract BossPattern getRandomPattern();

	public abstract int get_num_of_patterns_before_resting();

	public void nextPattern() {
		patterns.cycle();
		GenericListener start = () -> start(patterns.getState());

		// recovery time between two single patterns
		if (patterns.getState() instanceof BossRestPattern == false)
			new GameTimer(1 * Game.SEC, start);
		else
			start.doAction();
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		super.tick();

		// activation
		if (isActivated() == false) {
			if (new PlayerFinder().isPlayerCloserThan(this, 5))
				activate();
			else
				return;
		}

		if (patterns == null)
			startRandomPatternCycle();
	}

	private void startRandomPatternCycle() {
		BossPattern[] pattern_array = new BossPattern[0];

		for (int index = 0; index < get_num_of_patterns_before_resting(); index++)
			pattern_array = new ArrayCombiner<BossPattern>().combine(BossPattern.class, pattern_array,
					getRandomPattern());

		var rest = new BossRestPattern(this);
		pattern_array = new ArrayCombiner<BossPattern>().combine(BossPattern.class, pattern_array, rest);

		patterns = new Cycloid<>(pattern_array);
		start(patterns.getState());
	}

}
