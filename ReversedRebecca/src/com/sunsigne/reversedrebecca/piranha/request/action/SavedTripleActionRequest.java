package com.sunsigne.reversedrebecca.piranha.request.action;

import com.sunsigne.reversedrebecca.object.characteristics.interactive.TripleAction;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class SavedTripleActionRequest implements Request {

	////////// REQUEST ////////////

	public SavedTripleActionRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request request = new SavedTripleActionRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "SAVED_TRIPLE_ACTION";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {

		switch (target.toUpperCase()) {

		case "SAVE":
			TripleAction tripleAction = object.getTripleAction();
			object.setRegisteredTripleAction(tripleAction);
			break;
		case "LOAD":
			TripleAction registeredTripleAction = object.getRegisteredTripleAction();
			object.setTripleAction(registeredTripleAction);
			object.createTextAction();
			break;
		case "RESET":
			object.setRegisteredTripleAction(null);
			break;
		}
	}

}
