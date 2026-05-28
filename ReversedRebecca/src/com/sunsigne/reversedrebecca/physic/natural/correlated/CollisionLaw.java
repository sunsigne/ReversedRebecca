package com.sunsigne.reversedrebecca.physic.natural.correlated;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ConcurrentModificationException;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.characteristics.Velocity;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.debug.WallPassMode;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class CollisionLaw implements PhysicLaw {

	////////// TICK ////////////

	@Override
	public void tick(Updatable object) {
		if (object instanceof CollisionDetector == false)
			return;

		Handler layer = object.getHandler();
		if (layer == null)
			return;

		CollisionDetector detectorObject = (CollisionDetector) object;

		if (object instanceof Player == false) {

			// not moving & not a puzzle
			if (object instanceof Velocity && object.getHandler() != LAYER.PUZZLE.getHandler()) {
				if (((Velocity) object).isMotionless()) {
					processCollisionOnTheSpot(layer, object, detectorObject);
					return;
				}
			}
		}

		processCollisionEvent(layer, object, detectorObject);
	}

	private void processCollisionOnTheSpot(Handler layer, Updatable object, CollisionDetector detectorObject) {
		GameList<GameObject> list = Handler.getObjectsAtPos(layer, detectorObject.getX(), detectorObject.getY(), detectorObject.getSize(),
				true);
		
		processCollisionEvent(list, object, detectorObject);
	}

	private <T> void processCollisionEvent(GameList<T> list, Updatable object, CollisionDetector detectorObject) {

		try {
			for (T tempObject : list.getList()) {
				if (tempObject instanceof CollisionReactor == false)
					continue;

				CollisionReactor reactorObject = (CollisionReactor) tempObject;

				if (object == reactorObject)
					continue;

				if (objectAreColliding(detectorObject, reactorObject))
					reactorObject.collidingReaction(detectorObject);
			}
		} catch (ConcurrentModificationException e) {
			// some objects may disappear. As the next tick repair the problem,
			// this exception shouldn't not be a problem.
		}
	}

	private boolean objectAreColliding(CollisionDetector detectorObject, CollisionReactor reactorObject) {
		if (reactorObject == null)
			return false;

		if (detectorObject == reactorObject)
			return false;

		if (reactorObject instanceof Updatable && ((Updatable) reactorObject).getHandler() == null)
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