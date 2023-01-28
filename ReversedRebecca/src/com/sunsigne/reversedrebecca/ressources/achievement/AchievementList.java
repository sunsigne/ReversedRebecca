package com.sunsigne.reversedrebecca.ressources.achievement;

import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;

public class AchievementList {

	////////// MAP OR LIST ////////////

	private static GameList<Achievement> list = new GameList<>(LISTTYPE.ARRAY);

	public static GameList<Achievement> getList() {
		return list;
	}

}
