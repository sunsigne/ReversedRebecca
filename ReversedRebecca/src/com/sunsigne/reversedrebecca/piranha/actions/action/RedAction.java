package com.sunsigne.reversedrebecca.piranha.actions.action;

import com.sunsigne.reversedrebecca.piranha.actions.ActionList;
import com.sunsigne.reversedrebecca.piranha.actions.PiranhaObjectAction;

public class RedAction extends ColorAction {

	////////// NPC ACTION ////////////

	public RedAction() {
		ActionList.getList().addObject(this);
	}

	private static PiranhaObjectAction action = new RedAction();

	@Override
	public PiranhaObjectAction getAction() {
		return action;
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "RED";
	}

	////////// TEXT COLOR ////////////

	@Override
	public TEXT_COLOR getColor() {
		return TEXT_COLOR.RED;
	}

}
