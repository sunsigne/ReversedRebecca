package com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.actions.action;

import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.actions.ActionList;

public class WatchAction extends TalkAction {

	////////// NPC ACTION ////////////

	public WatchAction() {
		ActionList.getList().addObject(this);
	}

	private static ObjectAction action = new WatchAction();

	@Override
	public ObjectAction getAction() {
		return action;
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "WATCH";
	}

	////////// LISTENER ////////////

	@Override
	public String getType() {
		return "object-";
	}

	////////// KEYBOARD ////////////

	@Override
	public int getKeyEvent() {
		return KeyEvent.VK_E;
	}

}
