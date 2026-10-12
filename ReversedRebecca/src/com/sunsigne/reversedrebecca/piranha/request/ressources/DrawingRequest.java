package com.sunsigne.reversedrebecca.piranha.request.ressources;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.ressources.menu.drawing.DrawingTask;

public class DrawingRequest implements Request {

	////////// REQUEST ////////////

	public DrawingRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request action = new DrawingRequest();

	@Override
	public Request getRequest() {
		return action;
	}

	@Override
	public String getType() {
		return "DRAWING";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {
		new DrawingTask().unlock(target);
	}

}
