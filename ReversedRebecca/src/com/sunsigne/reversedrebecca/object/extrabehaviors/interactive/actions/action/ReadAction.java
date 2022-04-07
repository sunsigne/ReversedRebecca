package com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.actions.action;

import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.actions.ActionList;

public class ReadAction extends WatchAction {

	////////// NPC ACTION ////////////

	public ReadAction() {
		ActionList.getList().addObject(this);
	}

	private static ObjectAction action = new ReadAction();

	@Override
	public ObjectAction getAction() {
		return action;
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "READ";
	}

}
