package com.sunsigne.reversedrebecca.piranha.actions.action;

import com.sunsigne.reversedrebecca.piranha.actions.ActionList;
import com.sunsigne.reversedrebecca.piranha.actions.PiranhaObjectAction;

public class WatchAction extends TalkAction {

	////////// NPC ACTION ////////////

	public WatchAction() {
		ActionList.getList().addObject(this);
	}

	private static PiranhaObjectAction action = new WatchAction();

	@Override
	public PiranhaObjectAction getAction() {
		return action;
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "WATCH";
	}

}
