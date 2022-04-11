package com.sunsigne.reversedrebecca.piranha.actions.action;

import com.sunsigne.reversedrebecca.piranha.actions.ActionList;
import com.sunsigne.reversedrebecca.piranha.actions.ExtraBehaviorsObjectAction;

public class WatchAction extends TalkAction {

	////////// NPC ACTION ////////////

	public WatchAction() {
		ActionList.getList().addObject(this);
	}

	private static ExtraBehaviorsObjectAction action = new WatchAction();

	@Override
	public ExtraBehaviorsObjectAction getAction() {
		return action;
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "WATCH";
	}

}
