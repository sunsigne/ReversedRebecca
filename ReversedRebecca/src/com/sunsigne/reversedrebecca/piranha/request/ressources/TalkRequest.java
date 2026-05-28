package com.sunsigne.reversedrebecca.piranha.request.ressources;

import com.sunsigne.reversedrebecca.menu.chat.ChatBox;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.actions.PiranhaObjectAction;
import com.sunsigne.reversedrebecca.piranha.actions.action.TalkAction;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;
import com.sunsigne.reversedrebecca.world.controllers.UserCanInputRestartDialogue;

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
		UserCanInputRestartDialogue.lastChat = action;
		action.doAction();
	}

	private boolean multipleDialoguesSnitching(PiranhaObject object) {
		for (Updatable tempUpdatable : LAYER.PUZZLE.getHandler().getList()) {
			if (tempUpdatable instanceof ChatBox && ((ChatBox) tempUpdatable).isClosing() == false) {
				System.err.println("Problem encounter with following object : " + object.toString());
				System.err.println("A dialogue has been initiated while another dialogue was already running");
				return true;
			}
		}
		return false;
	}

}
