package com.sunsigne.reversedrebecca.object.collision;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.HandlerObject;
import com.sunsigne.reversedrebecca.object.Player;
import com.sunsigne.reversedrebecca.system.Conductor;
import com.sunsigne.reversedrebecca.system.main.ITick;
import com.sunsigne.reversedrebecca.util.Facing.DIRECTION;

public class CollisionDetector implements ITick {

	private GameObject collidingObject;

	public CollisionDetector(GameObject collidingObject) {
		this.collidingObject = collidingObject;
		startTick();
	}

	////////// EXISTING ////////////

	private boolean isObjectExisting() {
		return HandlerObject.getInstance().isObjectExisting(collidingObject);
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		if (!isObjectExisting())
			stopTick();

		boolean cameraDependant = collidingObject.isCameraDependant();
		boolean layerAbove = collidingObject.isLayerAbove();
		var list = HandlerObject.getInstance().getList(cameraDependant, layerAbove);

		for (GameObject tempObject : list) {

			if (collidingObject == tempObject)
				continue;

			if (collidingObjectIsCollinding(tempObject)) {
				ICollisionReaction collidedObject = (ICollisionReaction) tempObject;
				collidedObject.collidingReaction(collidingObject);
			}

		}
	}

	private boolean collidingObjectIsCollinding(GameObject tempObject) {

		if (tempObject instanceof ICollisionReaction == false)
			return false;

		if (tempObject instanceof Player && Conductor.DEBUG_MODE.getWallPassMode().getState())
			return false;

		ICollisionDetection iCollidingObject = (ICollisionDetection) collidingObject;

		if (iCollidingObject.getBounds(DIRECTION.LEFT, collidingObject).intersects(tempObject.getBounds()))
			return true;

		if (iCollidingObject.getBounds(DIRECTION.RIGHT, collidingObject).intersects(tempObject.getBounds()))
			return true;

		if (iCollidingObject.getBounds(DIRECTION.UP, collidingObject).intersects(tempObject.getBounds()))
			return true;

		if (iCollidingObject.getBounds(DIRECTION.DOWN, collidingObject).intersects(tempObject.getBounds()))
			return true;

		// should NOT occur
		return false;
	}
}
