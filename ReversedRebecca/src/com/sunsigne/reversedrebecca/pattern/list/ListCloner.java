package com.sunsigne.reversedrebecca.pattern.list;

public class ListCloner {

	public <T> GameList<T> deepClone(GameList<T> gameList) {
		var cloneList = new GameList<T>(LISTTYPE.ARRAY);

		try {
			cloneList.getList().addAll(gameList.getList());
		} catch (ArrayIndexOutOfBoundsException e) {
			// last element can be desynchronized
		}

		return cloneList;
	}

	public <T> GameLimitedList<T> deepClone(GameLimitedList<T> gameLimitedList) {
		var cloneList = new GameLimitedList<T>(LISTTYPE.ARRAY);

		try {
			cloneList.getList().addAll(gameLimitedList.getList());
		} catch (ArrayIndexOutOfBoundsException e) {
			// last element can be desynchronized
		}

		return cloneList;
	}

}
