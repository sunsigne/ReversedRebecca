package com.sunsigne.reversedrebecca.physic.natural.correlated;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.characteristics.Velocity;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.list.ListCloner;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.debug.WallPassMode;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class CollisionLaw implements PhysicLaw {

	////////// TICK ////////////

	@Override
	public void tick(Updatable object) {
		if (object instanceof CollisionDetector == false)
			return;

		CollisionDetector detectorObject = (CollisionDetector) object;
		CollisionReactor lastCollidedObject = detectorObject.getLastCollidedObject();

		
		if (object instanceof Player == false) {

			// still colliding last object
			if (objectAreColliding(detectorObject, lastCollidedObject)) {
				lastCollidedObject.collidingReaction(detectorObject);
				return;
			}

			// not moving
			if (object instanceof Velocity) {
				Velocity velocityObject = (Velocity) object;
				if (velocityObject.isMotionless()) {
					detectorObject.setLastCollidedObject(null);
					return;
				}					
			}
		}

		Handler layer = object.getHandler();
		if (layer == null)
			return;

		var list = new ListCloner().deepClone(layer);
		for (Updatable tempObject : list.getList()) {
			if (object == tempObject)
				continue;

			if (tempObject instanceof CollisionReactor == false)
				continue;

			CollisionReactor reactorObject = (CollisionReactor) tempObject;

			if (objectAreColliding(detectorObject, reactorObject)) {
				reactorObject.collidingReaction(detectorObject);
				detectorObject.setLastCollidedObject(reactorObject);
			}				
		}
	}

	private boolean objectAreColliding(CollisionDetector detectorObject, CollisionReactor reactorObject) {
		if (reactorObject == null)
			return false;

		if (detectorObject instanceof Player && WallPassMode.isActive())
			return false;

		Rectangle reactorObjectBounds = reactorObject.getBounds();

		if (detectorObject.getBounds().intersects(reactorObjectBounds) == false)
			return false;

		if (detectorObject.getBounds(DIRECTION.LEFT).intersects(reactorObjectBounds))
			return true;

		if (detectorObject.getBounds(DIRECTION.RIGHT).intersects(reactorObjectBounds))
			return true;

		if (detectorObject.getBounds(DIRECTION.UP).intersects(reactorObjectBounds))
			return true;

		if (detectorObject.getBounds(DIRECTION.DOWN).intersects(reactorObjectBounds))
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