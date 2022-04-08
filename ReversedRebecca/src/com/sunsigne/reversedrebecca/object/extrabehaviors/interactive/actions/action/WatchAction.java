package com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.actions.action;

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

}
