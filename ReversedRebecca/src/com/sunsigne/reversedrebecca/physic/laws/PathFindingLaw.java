package com.sunsigne.reversedrebecca.physic.laws;

import java.awt.Graphics;

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

		if (searcher.getX() % Size.M != 0 | searcher.getY() % Size.M != 0)
			return;

		// searcher has no goal
		if (searcher.getGoal() == searcher | searcher.getGoal() == null)
			return;

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