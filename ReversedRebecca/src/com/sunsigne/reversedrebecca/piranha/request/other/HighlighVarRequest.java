package com.sunsigne.reversedrebecca.piranha.request.other;

import com.sunsigne.reversedrebecca.object.piranha.InteractiveObject;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class HighlighVarRequest implements Request {

	////////// REQUEST ////////////

	public HighlighVarRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request request = new HighlighVarRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "HIGHLIGHT_VAR";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {
		if (object instanceof InteractiveObject == false)
			return;

		int var = Integer.parseInt(target);
		((InteractiveObject) object).loadHighlightVar(var);
	}

}