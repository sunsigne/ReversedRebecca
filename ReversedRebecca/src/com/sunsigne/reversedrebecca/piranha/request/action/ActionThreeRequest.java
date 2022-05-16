package com.sunsigne.reversedrebecca.piranha.request.action;

import com.sunsigne.reversedrebecca.object.characteristics.interactive.Action;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.TripleAction;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.actions.ActionAnalyzer;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class ActionThreeRequest implements Request {

	////////// REQUEST ////////////

	public ActionThreeRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request request = new ActionThreeRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "ACTION_THREE";
	}

	@Override
	public boolean hasCompactWriting() {
		return false;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {

		Action action = new ActionAnalyzer().getAction(object, target);

		TripleAction ta = object.getTripleAction();
		if (ta == null)
			ta = new TripleAction(null, null, null, null);
		
		TripleAction tripleAction = new TripleAction(ta.getNoActionText(), ta.getAction(0), ta.getAction(1), action);

		object.setTripleAction(tripleAction);
		object.createTextAction();
	}

}
