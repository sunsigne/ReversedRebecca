package com.sunsigne.reversedrebecca.piranha.request.interactive;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.Interactive;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.IndexRequest;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class EnableRequest implements IndexRequest {

	////////// REQUEST ////////////

	public EnableRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request action = new EnableRequest();

	@Override
	public Request getRequest() {
		return action;
	}

	@Override
	public String getType() {
		return "ENABLE";
	}

	@Override
	public boolean hasCompactWriting() {
		return false;
	}

	public boolean disable() {
		return false;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {

		GameObject gameObject = getGameObject(object, target);
		if (gameObject instanceof Interactive == false)
			return;

		Interactive interactive = (Interactive) gameObject;
		interactive.setDisabled(disable());
	}

}
