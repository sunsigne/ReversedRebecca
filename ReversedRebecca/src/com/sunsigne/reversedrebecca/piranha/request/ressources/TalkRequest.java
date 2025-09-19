package com.sunsigne.reversedrebecca.piranha.request.ressources;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.actions.PiranhaObjectAction;
import com.sunsigne.reversedrebecca.piranha.actions.action.TalkAction;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;

public class TalkRequest implements Request {

	////////// REQUEST ////////////

	public TalkRequest() {
		new RequestList().addRequest(this, getType());
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
		if (multipleDialoguesSnitching(object))
			return;

		PiranhaObjectAction action = new TalkAction();
		action.setListener(action.getListener(object, target));
		action.doAction();
	}

	private boolean multipleDialoguesSnitching(PiranhaObject object) {
		if (LAYER.PUZZLE.getHandler().getList().isEmpty())
			return false;

		System.err.println("Problem encounter with following object : " + object.toString());
		System.err.println("A dialogue has been initiated while another dialogue was already running");
		return true;
	}

}
