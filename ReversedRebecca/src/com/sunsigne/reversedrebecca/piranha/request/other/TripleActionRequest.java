package com.sunsigne.reversedrebecca.piranha.request.other;

import com.sunsigne.reversedrebecca.object.characteristics.interactive.Action;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.TripleAction;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.actions.ActionAnalyzer;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class TripleActionRequest implements Request {

	////////// REQUEST ////////////

	public TripleActionRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request request = new TripleActionRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "TRIPLE_ACTION";
	}

	@Override
	public boolean hasCompactWriting() {
		return false;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {

		String[] targets = new String[] {};
		if (target != null)
			targets = target.split(",");

		String actionInstruction1 = targets.length > 0 ? targets[0] : null;
		String actionInstruction2 = targets.length > 1 ? targets[1] : null;
		String actionInstruction3 = targets.length > 2 ? targets[2] : null;

		String noActionText = null;
		Action action1 = new ActionAnalyzer().getAction(object, actionInstruction1);
		Action action2 = new ActionAnalyzer().getAction(object, actionInstruction2);
		Action action3 = new ActionAnalyzer().getAction(object, actionInstruction3);

		TripleAction tripleAction = new TripleAction(noActionText, action1, action2, action3);

		object.setTripleAction(tripleAction);
		object.createTextAction();
	}

}
