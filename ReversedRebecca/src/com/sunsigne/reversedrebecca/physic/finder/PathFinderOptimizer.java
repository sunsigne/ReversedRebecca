package com.sunsigne.reversedrebecca.physic.finder;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.characteristics.PathSearcher;
import com.sunsigne.reversedrebecca.pattern.list.GameLimitedList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.system.mainloop.RenderFree;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class PathFinderOptimizer implements Updatable, RenderFree {

	////////// MAP OR LIST ////////////

	private static GameLimitedList<PathSearcher> list = new GameLimitedList<>(LISTTYPE.LINKED);

	////////// USEFUL ////////////

	public boolean mustWait(PathSearcher searcher, boolean allow_complex_path) {
		return list.containsObject(searcher) && allow_complex_path;
	}

	public void updateSearcher(DIRECTION path, PathSearcher searcher, boolean allow_complex_path) {
		if (path != DIRECTION.NULL)
			return;

		if (allow_complex_path == false)
			return;

		list.addObject(searcher);
	}

	////////// TICK ////////////

	private final int MAX_TIME = 11; // get a "big" PRIME number is really important here
	private int time = MAX_TIME;

	@Override
	public void tick() {
		time--;

		if (time <= 0) {
			time = MAX_TIME;
			list.clear();
		}
	}

}
