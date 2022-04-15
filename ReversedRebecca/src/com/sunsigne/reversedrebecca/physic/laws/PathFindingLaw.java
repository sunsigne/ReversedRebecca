package com.sunsigne.reversedrebecca.physic.laws;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.characteristics.Facing;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.characteristics.PathSearcher;
import com.sunsigne.reversedrebecca.physic.PathFinder;
import com.sunsigne.reversedrebecca.physic.PhysicList;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class PathFindingLaw implements PhysicLaw {

	////////// PHYSIC LAW ////////////

	public PathFindingLaw() {
		PhysicList.getList().addObject(this);
	}

	private static PhysicLaw physicLaw = new PathFindingLaw();

	@Override
	public PhysicLaw getPhysicLaw() {
		return physicLaw;
	}

	////////// TICK ////////////

	@Override
	public void tick(Updatable object) {
		if (object == null)
			return;

		if (object instanceof PathSearcher == false)
			return;

		PathSearcher searcher = (PathSearcher) object;

		// update only when centered on a tile ...
		if (searcher.getX() % Size.M != 0 | searcher.getY() % Size.M != 0) {
			if (searcher instanceof Facing == false)
				return;

			Facing facing = (Facing) searcher;
			// ... except if facing something else than the path
			if (facing.getFacing() == searcher.getPath())
				return;
		}

		// searcher has no goal
		if (searcher.getGoal() == searcher | searcher.getGoal() == null) {
			searcher.setPath(DIRECTION.NULL);
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