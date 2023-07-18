package com.sunsigne.reversedrebecca.object.piranha.living.bosses.blastx;

import com.sunsigne.reversedrebecca.object.piranha.living.bosses.BossObject;
import com.sunsigne.reversedrebecca.object.piranha.living.bosses.BossPattern;
import com.sunsigne.reversedrebecca.object.piranha.living.bosses.BossRestPattern;
import com.sunsigne.reversedrebecca.pattern.GameTimer;
import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.system.DifficultyOption;
import com.sunsigne.reversedrebecca.system.DifficultyOption.GAME_DIFFICULTY;

public class BlastXBoss extends BossObject {

	public BlastXBoss(int x, int y) {
		super("blast-x", x, y);
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

		list.addObject(new BlastXBombFromSkyPattern(this, 40));
		list.addObject(new BlastXThrowingBombPattern(this, 40));
		list.addObject(new BlastXThrowingBigBombPattern(this, 120));

		return new RandomGenerator().getElementFromList(list);
	}

}
