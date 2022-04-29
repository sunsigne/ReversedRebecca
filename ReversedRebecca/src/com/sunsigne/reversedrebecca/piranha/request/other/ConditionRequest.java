package com.sunsigne.reversedrebecca.piranha.request.other;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.object.piranha.living.characteristics.Feeling;
import com.sunsigne.reversedrebecca.object.piranha.living.characteristics.Feeling.CONDITION;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class ConditionRequest implements Request {

	////////// REQUEST ////////////

	public ConditionRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request action = new ConditionRequest();

	@Override
	public Request getRequest() {
		return action;
	}

	@Override
	public String getType() {
		return "CONDITION";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {
		if (object instanceof Feeling == false)
			return;

		Feeling feeling = (Feeling) object;
		CONDITION condition = CONDITION.GOOD;

		for (CONDITION tempCondition : CONDITION.values()) {
			if (tempCondition.getName().equalsIgnoreCase(target) == false)
				continue;

			condition = tempCondition;
		}
		feeling.setCondition(condition);
	}

}
