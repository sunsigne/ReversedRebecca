package com.sunsigne.reversedrebecca.piranha.request.ressources;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.actions.PiranhaObjectAction;
import com.sunsigne.reversedrebecca.piranha.actions.action.TalkAction;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.world.controllers.UserCanInputRestartDialogue;

public class TalkRequest implements Request {

	////////// REQUEST ////////////

	public TalkRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request request = new TalkRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "TALK";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {
		PiranhaObjectAction action = new TalkAction();
		action.setListener(action.getListener(object, target));
		UserCanInputRestartDialogue.lastChat = action;
		action.doAction();
	}

}
