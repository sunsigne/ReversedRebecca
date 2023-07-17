package com.sunsigne.reversedrebecca.object.piranha.living.bosses.blastx;

import com.sunsigne.reversedrebecca.object.piranha.living.bosses.BossObject;
import com.sunsigne.reversedrebecca.object.piranha.living.bosses.BossPattern;
import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;

public class BlastXBoss extends BossObject {

	public BlastXBoss(int x, int y) {
		super("blast-x", x, y);
	}

	////////// STATE ////////////

	@Override
	public int get_num_of_patterns_before_resting() {
		return 2;
	}

	////////// PATTERN ////////////

	private BossPattern getBombFromSkyPattern() {
		return new BlastXBombFromSkyPattern(this, 40);
	}

	private BossPattern getThrowingBombPattern() {
		return new BlastXThrowingBombPattern(this, 40);
	}

	@Override
	public BossPattern getRandomPattern() {
		var list = new GameList<BossPattern>(LISTTYPE.ARRAY);

		list.addObject(getBombFromSkyPattern());
		list.addObject(getThrowingBombPattern());

		return new RandomGenerator().getElementFromList(list);
	}

}
