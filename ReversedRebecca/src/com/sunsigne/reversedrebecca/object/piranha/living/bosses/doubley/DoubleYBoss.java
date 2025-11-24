package com.sunsigne.reversedrebecca.object.piranha.living.bosses.doubley;

import com.sunsigne.reversedrebecca.object.piranha.living.animation.DoubleYAnimationHandler;
import com.sunsigne.reversedrebecca.object.piranha.living.animation.LivingAnimationHandler;
import com.sunsigne.reversedrebecca.object.piranha.living.bosses.BossObject;
import com.sunsigne.reversedrebecca.object.piranha.living.bosses.BossPattern;
import com.sunsigne.reversedrebecca.object.piranha.living.bosses.BossRestPattern;
import com.sunsigne.reversedrebecca.pattern.GameTimer;
import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.system.DifficultyOption;
import com.sunsigne.reversedrebecca.system.DifficultyOption.GAME_DIFFICULTY;

public class DoubleYBoss extends BossObject implements DoubleYFeeling {

	public DoubleYBoss(int x, int y) {
		super("double-y", x, y);
	}

	////////// SIZE ////////////

	@Override
	public int getWidth() {
		return 6 * getPixelSize();
	}

	@Override
	public int getHeight() {
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
		switch (DifficultyOption.getDifficulty()) {
		case EASY:
			return 1;
		case NORMAL:
			return 2;
		case HARD:
			return 3;
		default:
			return 2;
		}
	}

	@Override
	public boolean updateFacingPlayer() {
		return false;
	}

	////////// VELOCICY ////////////

	// allow to bypass collision with all walls
	@Override
	public boolean isMotionless() {
		return true;
	}

	////////// PATH FINDER ////////////

	// allow to bypass the "round to tile" law
	@Override
	public boolean mustFollowPath() {
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

	@Override
	protected void start(BossPattern pattern, int delay) {

		// first patterns
		if (getEvolution() <= 1) {
			super.start(pattern, delay);
			return;
		}

		// game is set to easy
		if (DifficultyOption.getDifficulty() == GAME_DIFFICULTY.EASY) {
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

		BossPattern dual_pattern = getDifferentRandomPattern(pattern);
		new GameTimer(delay, () -> handler.addObject(pattern));
		new GameTimer(delay, () -> handler.addObject(dual_pattern));
	}

	////////// PATTERN ////////////

	@Override
	public BossPattern getRandomPattern() {
		var list = new GameList<BossPattern>(LISTTYPE.ARRAY);

		// list.addObject(new DoubleYFastPunchPattern(this));
		list.addObject(new DoubleYTornadoPattern(this));

		return new RandomGenerator().getElementFromList(list);
	}

	////////// COLLISION ////////////

	@Override
	public int getPixelSize() {
		return 24;
	}

}
