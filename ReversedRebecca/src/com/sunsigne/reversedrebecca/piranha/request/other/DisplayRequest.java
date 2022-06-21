package com.sunsigne.reversedrebecca.piranha.request.other;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.object.piranha.living.LivingObject;
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
		if (object instanceof LivingObject == false)
			return;

		LivingObject living = (LivingObject) object;
		Handler handler = living.getHandler();

		String[] targets = target.split(",");
		int x = Integer.valueOf(targets[0]);
		int y = Integer.valueOf(targets[1]);
		int z = getIndex(handler, targets[2].toUpperCase());

		living.setDisplayXY(x, y);
		handler.softRemoveObject(living);
		handler.getList().add(z, living);
	}

	private int getIndex(Handler handler, String value) {

		switch (value) {

		case "FRONT":
			return handler.getList().size() - 1;
		case "BACK":
			return 0;
		}

		return 0;
	}

}
