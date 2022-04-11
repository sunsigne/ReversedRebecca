package com.sunsigne.reversedrebecca.piranha.actions;

import com.sunsigne.reversedrebecca.pattern.list.GameLimitedList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;

public class ActionList {

	////////// MAP OR LIST ////////////

	private static GameLimitedList<ExtraBehaviorsObjectAction> list = new GameLimitedList<>(LISTTYPE.ARRAY);

	public static GameLimitedList<ExtraBehaviorsObjectAction> getList() {
		return list;
	}

}
