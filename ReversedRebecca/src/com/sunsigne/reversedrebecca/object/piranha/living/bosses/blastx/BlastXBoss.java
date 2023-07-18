package com.sunsigne.reversedrebecca.object.piranha.living.bosses.blastx;

import com.sunsigne.reversedrebecca.object.piranha.living.bosses.BossObject;
import com.sunsigne.reversedrebecca.object.piranha.living.bosses.BossPattern;
import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.system.DifficultyOption;

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

	////////// PATTERN ////////////

	private BossPattern getBombFromSkyPattern() {
		return new BlastXBombFromSkyPattern(this, 40);
	}

	private BossPattern getThrowingBombPattern() {
		return new BlastXThrowingBombPattern(this, 40);
	}

	private BossPattern getThrowingBigBombPattern() {
		return new BlastXThrowingBigBombPattern(this, 120);
	}

	@Override
	public BossPattern getRandomPattern() {
		var list = new GameList<BossPattern>(LISTTYPE.ARRAY);

		list.addObject(getBombFromSkyPattern());
		list.addObject(getThrowingBombPattern());
		list.addObject(getThrowingBigBombPattern());

		return new RandomGenerator().getElementFromList(list);
	}

}
