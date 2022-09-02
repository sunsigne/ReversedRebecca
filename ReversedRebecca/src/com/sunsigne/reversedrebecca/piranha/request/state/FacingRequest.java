package com.sunsigne.reversedrebecca.piranha.request.state;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.object.piranha.living.LivingObject;
import com.sunsigne.reversedrebecca.pattern.FormatedString;
import com.sunsigne.reversedrebecca.physic.finder.PathFinder;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.piranha.request.conditional.ConditionalRequest;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class FacingRequest extends ConditionalRequest {

	////////// REQUEST ////////////

	public FacingRequest() {
		RequestList.getList().addObject(this);
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
	public void doClassicAction(PiranhaObject object, String target) {

		// if facing is a clear direction (ex : UP, LEFT, etc.)
		for (DIRECTION tempFacing : DIRECTION.values()) {
			if (tempFacing.getName().equalsIgnoreCase(target)) {
				object.setMotionless();
				object.setFacing(tempFacing);
				return;
			}
		}

		// if facing is a character
		for (Updatable tempUpdatable : object.getHandler().getList()) {
			if (tempUpdatable instanceof LivingObject == false)
				continue;

			LivingObject tempLiving = (LivingObject) tempUpdatable;
			String formated_valueToCheck = new FormatedString().getName(object, target);

			if (tempLiving.getName().equalsIgnoreCase(formated_valueToCheck)) {
				PathFinder pathFinder = new PathFinder(object, tempLiving, false, false);
				object.setMotionless();
				if (pathFinder.getPath() != DIRECTION.NULL)
					object.setFacing(pathFinder.getPath());
				return;
			}
		}
	}

	@Override
	protected String getConditionToCheck(PiranhaObject object) {
		return object.getFacing().getName();
	}

}
