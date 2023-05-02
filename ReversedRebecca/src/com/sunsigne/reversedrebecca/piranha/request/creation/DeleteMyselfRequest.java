package com.sunsigne.reversedrebecca.piranha.request.creation;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;

public class DeleteMyselfRequest implements Request {

	////////// REQUEST ////////////

	public DeleteMyselfRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request request = new DeleteMyselfRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "DELETE_MYSELF";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {
		Handler handler = object.getHandler();
		if (handler != null)
			handler.removeObject(object);
	}

}
