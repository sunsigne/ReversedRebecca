package com.sunsigne.reversedrebecca.piranha.request.uncompact;

import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.physic.SightFinder;
import com.sunsigne.reversedrebecca.piranha.request.ConditionalRequest;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class SeeRequest extends ConditionalRequest {

	////////// REQUEST ////////////

	public SeeRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request request = new SeeRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "SEE";
	}

	@Override
	public boolean hasCompactWriting() {
		return false;
	}

	@Override
	public void doAction(ExtraBehaviorsObject object, String target) {
		doConditionalAction(object, target);
	}

	@Override
	protected String getConditionToCheck(ExtraBehaviorsObject object) {
		return object.getName();
	}

	@Override
	protected boolean analyseCondition(ExtraBehaviorsObject object, String target) {

		String valueToCheck = String.valueOf(target.split("\\?")[0]);

		for (Updatable tempUpdatable : object.getHandler().getList()) {
			if (tempUpdatable instanceof ExtraBehaviorsObject == false)
				continue;

			ExtraBehaviorsObject tempObject = (ExtraBehaviorsObject) tempUpdatable;

			if (getConditionToCheck(tempObject).equalsIgnoreCase(valueToCheck))
				return new SightFinder(object, tempObject).isGoalInSight();

			if (valueToCheck.equalsIgnoreCase("PLAYER"))
				return new SightFinder(object, new PlayerFinder().getPlayer()).isGoalInSight();
		}
		return false;
	}

}
