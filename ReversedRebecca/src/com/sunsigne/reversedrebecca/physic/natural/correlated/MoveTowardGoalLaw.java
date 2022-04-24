package com.sunsigne.reversedrebecca.physic.natural.correlated;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.characteristics.PathSearcher;
import com.sunsigne.reversedrebecca.object.characteristics.Stunnable;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class MoveTowardGoalLaw implements PhysicLaw {

	////////// TICK ////////////

	@Override
	public void tick(Updatable object) {
		if (object == null)
			return;

		// object does not have a PathFinding
		if (object instanceof PathSearcher == false)
			return;

		PathSearcher searcher = (PathSearcher) object;

		// object does not have an objective
		if (searcher.getPath() == null)
			return;

		// object is not supposed to move
		if (searcher.mustFollowPath() == false)
			return;

		// object is currently stunned
		if (object instanceof Stunnable) {
			Stunnable stunnable = (Stunnable) object;
			if (stunnable.isStunned())
				return;
		}

		searcher.setMotionless();
		followPath(searcher);
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
