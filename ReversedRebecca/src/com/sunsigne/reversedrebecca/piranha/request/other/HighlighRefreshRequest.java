package com.sunsigne.reversedrebecca.piranha.request.other;

import com.sunsigne.reversedrebecca.object.piranha.InteractiveObject;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class HighlighRefreshRequest implements Request {

	////////// REQUEST ////////////

	public HighlighRefreshRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request request = new HighlighRefreshRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "HIGHLIGHT_REFRESH";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {
		if (object instanceof InteractiveObject == false)
			return;

		((InteractiveObject) object).refreshHighlight();
	}

}