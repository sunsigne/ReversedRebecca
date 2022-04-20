package com.sunsigne.reversedrebecca.physic.laws;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.characteristics.PathSearcher;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class MoveTowardGoalLaw implements PhysicLaw {

	////////// TICK ////////////

	@Override
	public void tick(Updatable object) {
		if (object == null)
			return;

		if (object instanceof PathSearcher == false)
			return;

		PathSearcher searcher = (PathSearcher) object;

		if (searcher.getPath() == null)
			return;

		if (searcher.mustFollowPath())
			followPath(searcher);
		else
			searcher.setMotionless();
	}

	private void followPath(PathSearcher searcher) {

		switch (searcher.getPath()) {
		case NULL:
			searcher.setMotionless();
			break;
		case LEFT:
			searcher.setVelX(-searcher.getSpeed());
			break;
		case RIGHT:
			searcher.setVelX(searcher.getSpeed());
			break;
		case UP:
			searcher.setVelY(-searcher.getSpeed());
			break;
		case DOWN:
			searcher.setVelY(searcher.getSpeed());
			break;
		}
	}

	////////// RENDER ////////////

	@Override
	public void beforeObjectRender(Graphics g, Updatable object) {

	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {

	}

}
