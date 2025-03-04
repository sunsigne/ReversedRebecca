package com.sunsigne.reversedrebecca.piranha.request.conditional;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.pattern.FormattedString;
import com.sunsigne.reversedrebecca.pattern.list.ListCloner;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public abstract class ConditionalPatternRequest extends ConditionalRequest {

	////////// REQUEST ////////////

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

		var list = new ListCloner().deepClone(handler);
		for (Updatable tempUpdatable : list.getList()) {
			if (tempUpdatable instanceof PiranhaObject == false)
				continue;

			PiranhaObject tempObject = (PiranhaObject) tempUpdatable;
			String formated_valueToCheck = new FormattedString().getName(tempObject, valueToCheck);

			if (getConditionToCheck(tempObject).equalsIgnoreCase(formated_valueToCheck))
				return finalVerification(object, tempObject);
		}
		return false;
	}

	protected abstract boolean finalVerification(PiranhaObject object, PiranhaObject targetObject);

}
