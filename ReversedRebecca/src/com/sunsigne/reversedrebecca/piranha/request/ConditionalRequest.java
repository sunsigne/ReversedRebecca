package com.sunsigne.reversedrebecca.piranha.request;

import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.piranha.request.uncompact.GotoRequest;

public abstract class ConditionalRequest implements Request {

	////////// CONDITIONAL ////////////

	protected boolean isConditional(String target) {
		return target.contains("?");
	}

	protected void doConditionalAction(ExtraBehaviorsObject object, String target) {

		boolean isMet = analyseCondition(object, target);
		String action = defineAction(isMet, target);
		gotoAction(object, action);
	}

	///// analyse condition /////

	protected abstract String getConditionToCheck(ExtraBehaviorsObject object);

	protected boolean analyseCondition(ExtraBehaviorsObject object, String target) {
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

	private void gotoAction(ExtraBehaviorsObject object, String action) {
		Request request = RequestList.getList().getObject(new GotoRequest());
		request.doAction(object, action);
	}

}
