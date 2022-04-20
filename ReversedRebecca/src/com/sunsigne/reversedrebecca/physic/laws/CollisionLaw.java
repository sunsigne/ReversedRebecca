package com.sunsigne.reversedrebecca.physic.laws;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class CollisionLaw implements PhysicLaw {

	////////// TICK ////////////

	@Override
	public void tick(Updatable object) {
		if (object == null)
			return;

		CollisionDetector detectorObject = null;

		if (object instanceof CollisionDetector)
			detectorObject = (CollisionDetector) object;
		else
			return;

		Handler layer = object.getHandler();

		for (Updatable tempObject : layer.getList()) {
			if (object == tempObject)
				continue;

			CollisionReactor reactorObject = null;

			if (tempObject instanceof CollisionReactor)
				reactorObject = (CollisionReactor) tempObject;
			else
				continue;

			if (objectAreColliding(detectorObject, reactorObject))
				reactorObject.collidingReaction(detectorObject);
		}
	}

	private boolean objectAreColliding(CollisionDetector detectorObject, CollisionReactor reactorObject) {

		if (detectorObject.getBounds(DIRECTION.LEFT).intersects(reactorObject.getBounds()))
			return true;

		if (detectorObject.getBounds(DIRECTION.RIGHT).intersects(reactorObject.getBounds()))
			return true;

		if (detectorObject.getBounds(DIRECTION.UP).intersects(reactorObject.getBounds()))
			return true;

		if (detectorObject.getBounds(DIRECTION.DOWN).intersects(reactorObject.getBounds()))
			return true;

		return false;
	}

	////////// RENDER ////////////

	@Override
	public void beforeObjectRender(Graphics g, Updatable object) {

	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {

	}

}