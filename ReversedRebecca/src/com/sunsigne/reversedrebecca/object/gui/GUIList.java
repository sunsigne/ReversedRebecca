package com.sunsigne.reversedrebecca.object.gui;

import com.sunsigne.reversedrebecca.pattern.list.GameLimitedList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;

public class GUIList {

	////////// MAP OR LIST ////////////

	private static GameLimitedList<GUI> list = new GameLimitedList<>(LISTTYPE.ARRAY);

	public static GameLimitedList<GUI> getList() {
		return list;
	}

}
