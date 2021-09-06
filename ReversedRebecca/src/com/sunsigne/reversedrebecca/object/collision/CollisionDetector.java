package com.sunsigne.reversedrebecca.object.collision;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.HandlerObject;
import com.sunsigne.reversedrebecca.object.Player;
import com.sunsigne.reversedrebecca.system.Conductor;
import com.sunsigne.reversedrebecca.system.main.ITick;
import com.sunsigne.reversedrebecca.util.Facing.DIRECTION;

public class CollisionDetector implements ITick {

	private GameObject cDetectorObject;

	public CollisionDetector(GameObject cDetectorObject) {
		this.cDetectorObject = cDetectorObject;
	}

	////////// TICK ////////////

	@Override
	public void tick() {

		boolean cameraDependant = cDetectorObject.isCameraDependant();
		boolean layerAbove = cDetectorObject.isLayerAbove();
		var list = HandlerObject.getInstance().getList(cameraDependant, layerAbove);

		for (GameObject cReactorObject : list) {

			if (cDetectorObject == cReactorObject)
				continue;

			if (cDetectorObjectIsCollinding(cReactorObject)) {
				ICollisionReaction cReactorObject0 = (ICollisionReaction) cReactorObject;
				cReactorObject0.collidingReaction(cDetectorObject);
			}
		}
	}

	private boolean cDetectorObjectIsCollinding(GameObject cReactorObject) {

		if (cReactorObject instanceof ICollisionReaction == false)
			return false;

		if (cDetectorObject instanceof Player && Conductor.DEBUG_MODE.getWallPassMode().getState())
			return false;

		if (cDetectorObject.getBounds(DIRECTION.LEFT).intersects(cReactorObject.getBounds()))
			return true;

		if (cDetectorObject.getBounds(DIRECTION.RIGHT).intersects(cReactorObject.getBounds()))
			return true;

		if (cDetectorObject.getBounds(DIRECTION.UP).intersects(cReactorObject.getBounds()))
			return true;

		if (cDetectorObject.getBounds(DIRECTION.DOWN).intersects(cReactorObject.getBounds()))
			return true;

		// should NOT occur
		return false;
	}
}
