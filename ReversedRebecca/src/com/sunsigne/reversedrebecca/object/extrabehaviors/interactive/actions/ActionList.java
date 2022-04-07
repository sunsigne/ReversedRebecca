package com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.actions;

import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.actions.action.ObjectAction;
import com.sunsigne.reversedrebecca.pattern.list.GameLimitedList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;

public class ActionList {

	////////// MAP OR LIST ////////////

	private static GameLimitedList<ObjectAction> list = new GameLimitedList<>(LISTTYPE.ARRAY);

	public static GameLimitedList<ObjectAction> getList() {
		return list;
	}

}
