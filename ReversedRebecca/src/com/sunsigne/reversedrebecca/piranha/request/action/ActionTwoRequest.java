package com.sunsigne.reversedrebecca.piranha.request.action;

import com.sunsigne.reversedrebecca.object.characteristics.interactive.Action;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.TripleAction;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.actions.ActionAnalyzer;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class ActionTwoRequest implements Request {

	////////// REQUEST ////////////

	public ActionTwoRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request request = new ActionTwoRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "ACTION_TWO";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {

		Action action = new ActionAnalyzer().getAction(object, target);

		TripleAction ta = object.getTripleAction();
		if (ta == null)
			ta = new TripleAction(null, null, null, null);
		
		TripleAction tripleAction = new TripleAction(ta.getRequirementBubble(), ta.getAction(0), action, ta.getAction(2));

		object.setTripleAction(tripleAction);
		object.createTextAction();
	}

}
