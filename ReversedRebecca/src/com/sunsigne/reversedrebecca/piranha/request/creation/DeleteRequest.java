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
		new RequestList().addRequest(this, getType());
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

		String data = String.valueOf(target.split(":")[1]);
		String subLayer = String.valueOf(data.split(",")[0]);
		Handler handler = getSubLayer(object, subLayer);

		handler.removeObject(gameOject);
	}

}
