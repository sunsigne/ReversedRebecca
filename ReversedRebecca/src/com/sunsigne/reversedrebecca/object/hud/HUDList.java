package com.sunsigne.reversedrebecca.object.hud;

import com.sunsigne.reversedrebecca.pattern.list.GameLimitedList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;

public class HUDList {

	////////// MAP OR LIST ////////////

	private static GameLimitedList<HUD> list = new GameLimitedList<>(LISTTYPE.ARRAY);

	public static GameLimitedList<HUD> getList() {
		return list;
	}

}
