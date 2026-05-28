package com.sunsigne.reversedrebecca.object.piranha.living.bosses;

import com.sunsigne.reversedrebecca.object.piranha.living.LivingObject;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.ArrayCombiner;
import com.sunsigne.reversedrebecca.pattern.GameTimer;
import com.sunsigne.reversedrebecca.pattern.cycloid.Cycloid;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.physic.finder.PathFinder;
import com.sunsigne.reversedrebecca.system.PausePreventer;
import com.sunsigne.reversedrebecca.system.PausePreventer.PAUSE_STATE;

public abstract class BossObject extends LivingObject {

	public BossObject(String name, int x, int y) {
		super(name, x, y);
	}

	////////// USEFUL ////////////

	protected void start(BossPattern pattern) {
		start(pattern, 0);
	}

	protected void start(BossPattern pattern, int delay) {
		var handler = getHandler();
		if (handler != null)
			new GameTimer(delay, () -> handler.addObject(pattern));
		setHurtWhenPushing(true);
	}

	////////// PATH FINDER ////////////

	private boolean mustFollowPath;

	public void setMustFollowPath(boolean mustFollowPath) {
		this.mustFollowPath = mustFollowPath;
	}

	// allow to bypass the "round to tile" law
	@Override
	public boolean mustFollowPath() {
		return mustFollowPath;
	}

	////////// STATE ////////////

	private boolean activated;

	public boolean isActivated() {
		return activated;
	}

	public void activate() {
		setCondition(CONDITION.GOOD);
		setWalkingInPlace(updateFacingPlayer());
		PausePreventer.state = PAUSE_STATE.WIMP;
		this.activated = true;
	}

	public abstract boolean updateFacingPlayer();

	////////// EVOLUTION ////////////

	private int evolution;

	public int getEvolution() {
		return evolution;
	}

	public void evolve() {
		evolution++;
	}

	////////// PATTERN ////////////

	protected Cycloid<BossPattern> patterns;

	public abstract BossPattern getRandomPattern();

	public BossPattern getDifferentRandomPattern(BossPattern pattern) {
		BossPattern rad_pattern = getRandomPattern();

		while (rad_pattern.getClass() == pattern.getClass())
			rad_pattern = getRandomPattern();

		rad_pattern.setPatternTimeInSec(pattern.getPatternTimeInSec());
		rad_pattern.setActionWhenFinished(null);
		return rad_pattern;
	}

	public abstract int get_num_of_patterns_before_resting();

	public abstract int get_recovery_time_betweem_two_single_patterns();

	public void nextPattern() {
		if (patterns == null)
			return;

		setMotionless();
		patterns.cycle();

		// recovery time between two single patterns
		if (patterns.getState() instanceof BossRestPattern == false)
			start(patterns.getState(), get_recovery_time_betweem_two_single_patterns());
		else
			start(patterns.getState(), 30);
	}

	////////// TICK ////////////

	public void interrupt() {
		if (patterns != null)
			patterns.getState().interrupt();
	}

	@Override
	public void tick() {
		super.tick();

		// activation
		boolean firstAttack = false;
		if (isActivated() == false) {
			if (new PlayerFinder().isPlayerCloserThan(this, 6)) {
				activate();
				firstAttack = true;
			} else
				return;
		}

		// animation
		facingPlayer();

		if (patterns == null)
			startRandomPatternCycle(firstAttack);
	}

	private void facingPlayer() {
		if (updateFacingPlayer() == false)
			return;

		Player player = new PlayerFinder().getPlayer();
		if (player == null)
			return;

		PathFinder pathFinder = new PathFinder(this, player);
		if (pathFinder.getPath() != DIRECTION.NULL) {
			setFacing(pathFinder.getPath());
		}
	}

	protected void startRandomPatternCycle(boolean firstAttack) {
		BossPattern[] pattern_array = new BossPattern[0];
		BossPattern pattern = null;

		for (int index = 0; index < get_num_of_patterns_before_resting(); index++) {
			pattern = getRandomPattern();

			if (index > 0) {
				while (pattern.getClass() == pattern_array[index - 1].getClass() && pattern_array.length > 1)
					pattern = getRandomPattern();
			}

			pattern_array = new ArrayCombiner<BossPattern>().combine(BossPattern.class, pattern_array, pattern);
		}

		var rest = new BossRestPattern(this);
		pattern_array = new ArrayCombiner<BossPattern>().combine(BossPattern.class, pattern_array, rest);

		patterns = new Cycloid<>(pattern_array);
		start(patterns.getState(), firstAttack ? 0 : 60);
	}

}
