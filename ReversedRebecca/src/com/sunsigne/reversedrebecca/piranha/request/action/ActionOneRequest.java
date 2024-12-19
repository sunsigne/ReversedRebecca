package com.sunsigne.reversedrebecca.piranha.request.action;

import com.sunsigne.reversedrebecca.object.characteristics.interactive.Action;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.TripleAction;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.actions.ActionAnalyzer;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class ActionOneRequest implements Request {

	////////// REQUEST ////////////

	public ActionOneRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request request = new ActionOneRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "ACTION_ONE";
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

		TripleAction tripleAction = new TripleAction(ta.getRequirementBubble(), action, ta.getAction(1),
				ta.getAction(2));

		object.setTripleAction(tripleAction);
		object.createTextAction();
	}

}
