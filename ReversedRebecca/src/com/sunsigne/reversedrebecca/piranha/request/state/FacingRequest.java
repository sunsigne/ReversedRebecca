package com.sunsigne.reversedrebecca.piranha.request.state;

import java.util.Collections;
import java.util.HashMap;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.object.piranha.living.characteristics.PlayerAvoider;
import com.sunsigne.reversedrebecca.object.piranha.living.characteristics.PlayerAvoider.AVOIDERTYPE;
import com.sunsigne.reversedrebecca.pattern.FormattedString;
import com.sunsigne.reversedrebecca.pattern.list.ListCloner;
import com.sunsigne.reversedrebecca.physic.finder.SightFinder;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.piranha.request.conditional.ConditionalRequest;

public class FacingRequest extends ConditionalRequest {

	////////// REQUEST ////////////

	public FacingRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request request = new FacingRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "FACING";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	@Override
	protected String getConditionToCheck(PiranhaObject object) {
		return object.getFacing().getName();
	}

	@Override
	public void doClassicAction(PiranhaObject object, String target) {

		// if facing is a clear direction (ex : UP, LEFT, etc.)
		for (DIRECTION tempFacing : DIRECTION.values()) {
			if (tempFacing.getName().equalsIgnoreCase(target)) {
				paralyseObject(object);
				object.setFacing(tempFacing);
				return;
			}
		}

		// if facing is an object
		var handler = object.getHandler();

		var list = new ListCloner().deepCloneByClass(handler, PiranhaObject.class);
		HashMap<Float, PiranhaObject> map = new HashMap<>();

		for (PiranhaObject tempPiranha : list.getList()) {
			String formated_valueToCheck = new FormattedString().getName(object, target, false);
			if (tempPiranha.getName().equalsIgnoreCase(formated_valueToCheck) == false)
				continue;

			float diffX = object.getX() - (tempPiranha.getX());
			float diffY = object.getY() - (tempPiranha.getY());
			float distance = (float) Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2));
			map.put(distance, tempPiranha);
		}

		PiranhaObject closet = map.get(Collections.min(map.keySet()));
		SightFinder sightFinder = new SightFinder(object, closet);
		DIRECTION facing = sightFinder.getDirectionOfGoalFromObserver();
		if (facing != DIRECTION.NULL) {
			paralyseObject(object);
			object.setFacing(facing);
		}
		return;
	}

	private void paralyseObject(PiranhaObject object) {
		if (object instanceof PlayerAvoider) {
			PlayerAvoider avoider = (PlayerAvoider) object;
			if (avoider.getPlayerAvoiderType() == AVOIDERTYPE.STOP)
				return;
		}

		object.setMotionless();
	}

}
