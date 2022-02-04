package com.sunsigne.reversedrebecca.characteristics.tools;

import com.sunsigne.reversedrebecca.pattern.list.GameLimitedList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;

public class ToolPlayerList {

	////////// MAP OR LIST ////////////
	
	private static GameLimitedList<ToolPlayer> list = new GameLimitedList<>(LISTTYPE.ARRAY);

	public static GameLimitedList<ToolPlayer> getList() {
		return list;
	}
	
}
