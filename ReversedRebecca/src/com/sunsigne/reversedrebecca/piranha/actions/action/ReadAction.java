package com.sunsigne.reversedrebecca.piranha.actions.action;

import com.sunsigne.reversedrebecca.piranha.actions.ActionList;
import com.sunsigne.reversedrebecca.piranha.actions.PiranhaObjectAction;

public class ReadAction extends WatchAction {

	////////// NPC ACTION ////////////

	public ReadAction() {
		ActionList.getList().addObject(this);
	}

	private static PiranhaObjectAction action = new ReadAction();

	@Override
	public PiranhaObjectAction getAction() {
		return action;
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "READ";
	}

}
