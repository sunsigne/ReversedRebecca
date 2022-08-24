package com.sunsigne.reversedrebecca.piranha.request.conditional;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.pattern.FormatedString;
import com.sunsigne.reversedrebecca.physic.finder.SightFinder;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;
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
	public void doClassicAction(PiranhaObject object, String target) {

	}

	@Override
	protected String getConditionToCheck(PiranhaObject object) {
		return object.getName();
	}

	@Override
	protected boolean analyseCondition(PiranhaObject object, String target) {

		String valueToCheck = String.valueOf(target.split("\\?")[0]);
		Handler handler = object.getHandler();

		for (Updatable tempUpdatable : handler.getList()) {
			if (tempUpdatable instanceof PiranhaObject == false)
				continue;

			PiranhaObject tempObject = (PiranhaObject) tempUpdatable;
			String formated_valueToCheck = new FormatedString().getName(tempObject, valueToCheck);

			if (getConditionToCheck(tempObject).equalsIgnoreCase(formated_valueToCheck))
				return new SightFinder(object, tempObject).isGoalInSight();
		}
		return false;
	}

}
