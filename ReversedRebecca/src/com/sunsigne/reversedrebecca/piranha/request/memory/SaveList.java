package com.sunsigne.reversedrebecca.piranha.request.memory;

import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;

public class SaveList {

	////////// MAP OR LIST ////////////

	private static GameList<String> list = new GameList<>(LISTTYPE.ARRAY);

	public static GameList<String> getList() {
		return list;
	}

}
