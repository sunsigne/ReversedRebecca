package com.sunsigne.reversedrebecca.piranha.request.state;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.object.piranha.living.characteristics.Feeling;
import com.sunsigne.reversedrebecca.object.piranha.living.characteristics.Feeling.CONDITION;
import com.sunsigne.reversedrebecca.piranha.request.ConditionalRequest;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class ConditionRequest extends ConditionalRequest {

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
	public void doClassicAction(PiranhaObject object, String target) {
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

	@Override
	protected String getConditionToCheck(PiranhaObject object) {
		if (object instanceof Feeling == false)
			return "false";

		Feeling feeling = (Feeling) object;
		return feeling.getCondition().getName();
	}

}
