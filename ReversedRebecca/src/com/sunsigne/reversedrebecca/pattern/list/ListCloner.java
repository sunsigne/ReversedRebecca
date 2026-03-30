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

	@SuppressWarnings("unchecked")
	public <T, U> GameList<U> deepCloneByClass(GameList<T> gameList, Class<U> clazz) {
		var cloneList = new GameList<U>(LISTTYPE.ARRAY);

		try {
			for (int index = 0; index < gameList.getList().size(); index++) {
				T tempObject = gameList.getList().get(index);
				if (clazz.isInstance(tempObject))
					cloneList.addObject((U) tempObject);
			}

		} catch (ArrayIndexOutOfBoundsException e) {
			// last element can be desynchronized
		}

		return cloneList;
	}
	
	@SuppressWarnings("unchecked")
	public <T, U> GameLimitedList<U> deepCloneByClass(GameLimitedList<T> gameList, Class<U> clazz) {
		var cloneList = new GameLimitedList<U>(LISTTYPE.ARRAY);

		try {
			for (int index = 0; index < gameList.getList().size(); index++) {
				T tempObject = gameList.getList().get(index);
				if (clazz.isInstance(tempObject))
					cloneList.addObject((U) tempObject);
			}

		} catch (ArrayIndexOutOfBoundsException e) {
			// last element can be desynchronized
		}

		return cloneList;
	}

}
