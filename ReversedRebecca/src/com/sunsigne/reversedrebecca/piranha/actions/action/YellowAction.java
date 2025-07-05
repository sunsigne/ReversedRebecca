package com.sunsigne.reversedrebecca.piranha.actions.action;

import com.sunsigne.reversedrebecca.piranha.actions.ActionList;
import com.sunsigne.reversedrebecca.piranha.actions.PiranhaObjectAction;

public class YellowAction extends ColorAction {

	////////// NPC ACTION ////////////

	public YellowAction() {
		ActionList.getList().addObject(this);
	}

	private static PiranhaObjectAction action = new YellowAction();

	@Override
	public PiranhaObjectAction getAction() {
		return action;
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "YELLOW";
	}

	////////// TEXT COLOR ////////////

	@Override
	public TEXT_COLOR getColor() {
		return TEXT_COLOR.YELLOW;
	}

}
