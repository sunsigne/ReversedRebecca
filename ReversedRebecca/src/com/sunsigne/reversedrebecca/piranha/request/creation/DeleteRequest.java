package com.sunsigne.reversedrebecca.piranha.request.creation;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.IndexRequest;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;

public class DeleteRequest implements IndexRequest {

	////////// REQUEST ////////////

	public DeleteRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request request = new DeleteRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "DELETE";
	}

	@Override
	public boolean hasCompactWriting() {
		return false;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {

		GameObject gameOject = getGameObject(object, target);
		Handler handler = object.getHandler();
		handler.removeObject(gameOject);
	}

}
