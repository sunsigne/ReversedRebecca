package com.sunsigne.reversedrebecca.piranha.request.conditional;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.gotoo.GotoRequest;

public abstract class ConditionalRequest implements Request {

	////////// REQUEST ////////////

	@Override
	public void doAction(PiranhaObject object, String target) {
		if (isConditional(target))
			doConditionalAction(object, target);
		else
			doClassicAction(object, target);
	}

	public abstract void doClassicAction(PiranhaObject object, String target);

	////////// CONDITIONAL ////////////

	protected boolean isConditional(String target) {
		return target.contains("?");
	}

	protected void doConditionalAction(PiranhaObject object, String target) {

		boolean isMet = analyseCondition(object, target);
		String action = defineAction(isMet, target);
		gotoAction(object, action);
	}

	///// analyse condition /////

	protected abstract String getConditionToCheck(PiranhaObject object);

	protected boolean analyseCondition(PiranhaObject object, String target) {
		String valueToCheck = String.valueOf(target.split("\\?")[0]);
		return getConditionToCheck(object).equalsIgnoreCase(valueToCheck);
	}

	///// define action /////

	private String defineAction(boolean isMet, String target) {
		String actions = String.valueOf(target.split("\\?")[1]);
		String trueAction = String.valueOf(actions.split("\\/")[0]);
		String falseAction = String.valueOf(actions.split("\\/")[1]);

		return isMet ? trueAction : falseAction;
	}

	///// goto action /////

	private void gotoAction(PiranhaObject object, String action) {
		Request request = new GotoRequest();
		request.doAction(object, action);
	}

}
