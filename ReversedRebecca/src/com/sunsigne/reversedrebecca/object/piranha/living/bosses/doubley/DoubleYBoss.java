package com.sunsigne.reversedrebecca.object.piranha.living.bosses.doubley;

import com.sunsigne.reversedrebecca.object.piranha.living.animation.DoubleYAnimationHandler;
import com.sunsigne.reversedrebecca.object.piranha.living.animation.LivingAnimationHandler;
import com.sunsigne.reversedrebecca.object.piranha.living.bosses.BossObject;
import com.sunsigne.reversedrebecca.object.piranha.living.bosses.BossPattern;
import com.sunsigne.reversedrebecca.object.piranha.living.bosses.BossRestPattern;
import com.sunsigne.reversedrebecca.object.piranha.living.bosses.DoubleYRestPattern;
import com.sunsigne.reversedrebecca.pattern.ArrayCombiner;
import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.pattern.cycloid.Cycloid;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;

public class DoubleYBoss extends BossObject implements DoubleYFeeling {

	public DoubleYBoss(int x, int y) {
		super("double-y", x, y);
	}

	////////// NAME ////////////

	@Override
	public String getTextureName() {
		if (isSSJ2())
			return super.getTextureName() + "_ssj2";

		return super.getTextureName();
	}

	////////// SIZE ////////////

	@Override
	public int getWidth() {
		return 6 * getPixelSize();
	}

	@Override
	public int getHeight() {
		if (isSSJ2())
			return 8 * getPixelSize();

		return 6 * getPixelSize();
	}

	////////// DOUBLE Y CONDITION ////////////

	private DOUBLE_Y_CONDITION doubleYCondition = DOUBLE_Y_CONDITION.GOOD;

	@Override
	public DOUBLE_Y_CONDITION getDoubleYCondition() {
		return doubleYCondition;
	}

	@Override
	public void setDoubleYCondition(DOUBLE_Y_CONDITION doubleYCondition) {
		this.doubleYCondition = doubleYCondition;
	}

	////////// STATE ////////////

	@Override
	public int get_num_of_patterns_before_resting() {
		switch (getEvolution()) {
		case 1:
		default:
			return 2;
		}
	}

	@Override
	public boolean updateFacingPlayer() {
		return false;
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		animation.run();
		super.tick();
	}

	////////// TEXTURE ////////////

	@Override
	protected void loadAnimationHandler(LivingAnimationHandler animationHandler) {
		super.loadAnimationHandler(new DoubleYAnimationHandler(this));
	}

	////////// EVOLUTION ////////////

	private boolean isSSJ2() {
		return getEvolution() % 2 != 0;
	}

	@Override
	protected void start(BossPattern pattern, int delay) {
		setPushingDirection(null);
		setDoubleYCondition(DOUBLE_Y_CONDITION.GOOD);

		// first patterns
		if (getEvolution() <= 1) {
			super.start(pattern, delay);
			return;
		}

		// boss is resting
		if (pattern instanceof BossRestPattern) {
			super.start(pattern, delay);
			return;
		}

		// no handler found
		var handler = getHandler();
		if (handler == null)
			return;
	}

	@Override
	public void evolve() {
		super.evolve();
		loadAnimationHandler(new DoubleYAnimationHandler(this));
		setDisplayXY(0, isSSJ2() ? -8 : 0);
	}

	////////// PATTERN ////////////

	@Override
	public int get_recovery_time_betweem_two_single_patterns() {
		return 40;
	}

	// unused
	@Override
	public BossPattern getRandomPattern() {
		var list = new GameList<BossPattern>(LISTTYPE.ARRAY);

		list.addObject(new DoubleYFastPunchPattern(this));
		list.addObject(new DoubleYTornadoPattern(this));

		return new RandomGenerator().getElementFromList(list);
	}

	@Override
	protected void startRandomPatternCycle(boolean firstAttack) {
		BossPattern[] pattern_array = new BossPattern[0];

		BossPattern fastPunch = new DoubleYFastPunchPattern(this);
		BossPattern tornado = new DoubleYTornadoPattern(this);
		BossPattern rest = new DoubleYRestPattern(this);

		if (getEvolution() < 1)
			pattern_array = new ArrayCombiner<BossPattern>().combine(BossPattern.class, pattern_array, fastPunch,
					tornado, rest);

		else
			return;

		patterns = new Cycloid<>(pattern_array);
		start(patterns.getState(), firstAttack ? 0 : 60);
	}

	////////// COLLISION ////////////

	@Override
	public int getPixelSize() {
		return 24;
	}

}
