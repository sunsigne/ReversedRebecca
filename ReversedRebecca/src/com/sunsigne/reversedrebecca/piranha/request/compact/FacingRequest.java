package com.sunsigne.reversedrebecca.piranha.request.compact;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.LivingObject;
import com.sunsigne.reversedrebecca.physic.PathFinder;
import com.sunsigne.reversedrebecca.piranha.ConditionalAnalyser;
import com.sunsigne.reversedrebecca.piranha.RequestList;
import com.sunsigne.reversedrebecca.piranha.request.GotoRequest;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class FacingRequest implements Request {

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
	public void doAction(ExtraBehaviorsObject object, String target) {

		ConditionalAnalyser conditional = ConditionalAnalyser.create(target);

		if (conditional != null)
			checkFacing(conditional, object, target);
		else
			doFacing(object, target);
	}

	private void checkFacing(ConditionalAnalyser conditional, ExtraBehaviorsObject object, String target) {

		if (object.getFacing().getName().equalsIgnoreCase(conditional.getValueToCheck()))
			conditional.setMet(true);

		Request instruction = RequestList.getList().getObject(new GotoRequest());
		instruction.doAction(object, conditional.getAction());
	}

	private void doFacing(ExtraBehaviorsObject object, String target) {

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

			if (tempLiving.getName().equalsIgnoreCase(target)) {
				PathFinder pathFinder = new PathFinder(object, tempLiving, true);
				object.setMotionless();
				object.setFacing(pathFinder.getPath());
				return;
			}
		}
	}

}
