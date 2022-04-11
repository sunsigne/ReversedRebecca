package com.sunsigne.reversedrebecca.piranha.actions.action;

import com.sunsigne.reversedrebecca.piranha.actions.ActionList;
import com.sunsigne.reversedrebecca.piranha.actions.ExtraBehaviorsObjectAction;

public class ReadAction extends WatchAction {

	////////// NPC ACTION ////////////

	public ReadAction() {
		ActionList.getList().addObject(this);
	}

	private static ExtraBehaviorsObjectAction action = new ReadAction();

	@Override
	public ExtraBehaviorsObjectAction getAction() {
		return action;
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "READ";
	}

}
