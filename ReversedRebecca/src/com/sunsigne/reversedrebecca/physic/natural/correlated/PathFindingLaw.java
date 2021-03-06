package com.sunsigne.reversedrebecca.physic.natural.correlated;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.characteristics.Facing;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.characteristics.PathSearcher;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.finder.PathFinder;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class PathFindingLaw implements PhysicLaw {

	////////// TICK ////////////

	@Override
	public void tick(Updatable object) {
		if (object instanceof PathSearcher == false)
			return;

		PathSearcher searcher = (PathSearcher) object;

		// searcher has no goal
		if (searcher.getGoal() == searcher | searcher.getGoal() == null) {
			searcher.setPath(DIRECTION.NULL);
			return;
		}

		// update only when centered on a tile ...
		if (searcher.getX() % searcher.getSize() != 0 | searcher.getY() % searcher.getSize() != 0) {
			if (searcher instanceof Facing == false)
				return;

			Facing facing = (Facing) searcher;
			// ... except if facing something else than the path
			if (facing.getFacing() == searcher.getPath())
				return;
		}

		// search path
		PathFinder pathFinder = new PathFinder(searcher, searcher.getGoal(), true);
		searcher.setPath(pathFinder.getPath());
	}

	////////// RENDER ////////////

	@Override
	public void beforeObjectRender(Graphics g, Updatable object) {

	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {

	}

}