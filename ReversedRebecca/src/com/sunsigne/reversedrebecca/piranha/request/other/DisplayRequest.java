package com.sunsigne.reversedrebecca.piranha.request.other;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;

public class DisplayRequest implements Request {

	////////// REQUEST ////////////

	public DisplayRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request request = new DisplayRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "DISPLAY";
	}

	@Override
	public boolean hasCompactWriting() {
		return false;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {
		Handler handler = object.getHandler();
		int pos = getIndex(handler, object, target.toUpperCase());

		handler.softRemoveObject(object);
		handler.getList().add(pos, object);

	}

	private int getIndex(Handler handler, PiranhaObject object, String value) {

		switch (value) {

		case "FRONT":
			return handler.getList().size() - 1;
		case "BACK":
			return 0;
		}

		return 0;
	}

}
