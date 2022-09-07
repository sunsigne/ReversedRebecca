package com.sunsigne.reversedrebecca.physic.finder;

import com.sunsigne.reversedrebecca.object.PathPointObject;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.characteristics.PathSearcher;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.system.mainloop.RenderFree;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class PathFinderOptimizer implements Updatable, RenderFree {

	////////// MAP OR LIST ////////////

	private static GameList<PathSearcher> list = new GameList<>(LISTTYPE.LINKED);

	////////// OPTIMIZER ////////////

	private boolean canBeOptimized(PathSearcher searcher) {
		return searcher instanceof PathPointObject == false;
	}

	protected boolean mustWait(PathSearcher searcher) {
		if (canBeOptimized(searcher))
			return list.containsObject(searcher);
		else
			return false;
	}

	protected void updateSearcher(PathSearcher searcher, DIRECTION path) {
		if (canBeOptimized(searcher) == false)
			return;

		if (path != DIRECTION.NULL)
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
