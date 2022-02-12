package com.sunsigne.reversedrebecca.characteristics.tools;

import com.sunsigne.reversedrebecca.characteristics.Characteristic;
import com.sunsigne.reversedrebecca.pattern.list.GameLimitedList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;

public class ToolPlayerList extends Characteristic {
	
	@SuppressWarnings("unused") // instantiate this class is necessary
	private static ToolPlayerList instance = new ToolPlayerList(); 
	
	////////// MAP OR LIST ////////////

	private static GameLimitedList<ToolPlayer> list = new GameLimitedList<>(LISTTYPE.ARRAY);

	public static GameLimitedList<ToolPlayer> getList() {
		return list;
	}

	////////// CHARACTERISTICS ////////////

	@Override
	public void reset() {
		list.clear();
	}

}
