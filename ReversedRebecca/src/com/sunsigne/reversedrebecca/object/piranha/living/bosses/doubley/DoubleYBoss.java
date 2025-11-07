package com.sunsigne.reversedrebecca.object.piranha.living.bosses.doubley;

import com.sunsigne.reversedrebecca.object.piranha.living.animation.DoubleYAnimationHandler;
import com.sunsigne.reversedrebecca.object.piranha.living.animation.LivingAnimationHandler;
import com.sunsigne.reversedrebecca.object.piranha.living.bosses.BossObject;
import com.sunsigne.reversedrebecca.object.piranha.living.bosses.BossPattern;
import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.system.DifficultyOption;

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

	////////// TICK ////////////

	@Override
	public void tick() {
		animation.run();
	}

	////////// TEXTURE ////////////

	@Override
	protected void loadAnimationHandler(LivingAnimationHandler animationHandler) {
		super.loadAnimationHandler(new DoubleYAnimationHandler(this));
	}

	////////// EVOLUTION ////////////

	@Override
	protected void start(BossPattern pattern, int delay) {

	}

	////////// PATTERN ////////////

	@Override
	public BossPattern getRandomPattern() {
		var list = new GameList<BossPattern>(LISTTYPE.ARRAY);

		// list.addObject(new BlastXBombFromSkyPattern(this));
		// list.addObject(new BlastXThrowingBombPattern(this));
		// list.addObject(new BlastXThrowingCurvedBombPattern(this));
		// list.addObject(new BlastXThrowingBigBombPattern(this));
		// list.addObject(new BlastXThrowingGhostBombPattern(this));

		return new RandomGenerator().getElementFromList(list);
	}

	////////// COLLISION ////////////

	@Override
	public int getPixelSize() {
		return 24;
	}

}
