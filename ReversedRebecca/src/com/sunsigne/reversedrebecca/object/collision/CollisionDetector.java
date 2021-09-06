package com.sunsigne.reversedrebecca.object.collision;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.HandlerObject;
import com.sunsigne.reversedrebecca.object.Player;
import com.sunsigne.reversedrebecca.system.Conductor;
import com.sunsigne.reversedrebecca.system.main.ITick;
import com.sunsigne.reversedrebecca.util.Facing.DIRECTION;

public class CollisionDetector implements ITick {

	private GameObject clnDetectorObject;

	public CollisionDetector(GameObject clnDetectorObject) {
		this.clnDetectorObject = clnDetectorObject;
	}

	////////// TICK ////////////

	@Override
	public void tick() {

		boolean cameraDependant = clnDetectorObject.isCameraDependant();
		boolean layerAbove = clnDetectorObject.isLayerAbove();
		var list = HandlerObject.getInstance().getList(cameraDependant, layerAbove);

		for (GameObject clnReactorObject : list) {

			if (clnDetectorObject == clnReactorObject)
				continue;

			if (clnDetectorObjectIsCollinding(clnReactorObject)) {
				ICollisionReaction clnReactorObject0 = (ICollisionReaction) clnReactorObject;
				clnReactorObject0.collidingReaction(clnDetectorObject);
			}
		}
	}

	private boolean clnDetectorObjectIsCollinding(GameObject clnReactorObject) {

		if (clnReactorObject instanceof ICollisionReaction == false)
			return false;

		if (clnDetectorObject instanceof Player && Conductor.DEBUG_MODE.getWallPassMode().getState())
			return false;

		if (clnDetectorObject.getBounds(DIRECTION.LEFT).intersects(clnReactorObject.getBounds()))
			return true;

		if (clnDetectorObject.getBounds(DIRECTION.RIGHT).intersects(clnReactorObject.getBounds()))
			return true;

		if (clnDetectorObject.getBounds(DIRECTION.UP).intersects(clnReactorObject.getBounds()))
			return true;

		if (clnDetectorObject.getBounds(DIRECTION.DOWN).intersects(clnReactorObject.getBounds()))
			return true;

		// should NOT occur
		return false;
	}
}
