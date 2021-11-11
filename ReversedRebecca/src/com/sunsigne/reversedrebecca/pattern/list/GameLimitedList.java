package com.sunsigne.reversedrebecca.pattern.list;

public class GameLimitedList<T> extends GameList<T> {

	public GameLimitedList(LISTTYPE listType) {
		super(listType);
	}

	////////// MAP OR LIST ////////////
	
	@Override
	public boolean cointainsObject(T object) {
		if (object == null)
			return false;

		Object obj = object.getClass();

		for (T tempObject : getList()) {
			Object tempObj = tempObject.getClass();
			if (obj == tempObj)
				return true;
		}
		return false;
	}

	public T getObject(T object) {
		if (object == null)
			return null;

		T obj = null;

		for (T tempObject : getList()) {
			if (tempObject.getClass() == object.getClass())
				obj = tempObject;
		}
		return obj;
	}
	
	@Override
	public void removeObject(T object) {
		if (object == null || !cointainsObject(object))
			return;

		getList().remove(getObject(object));
	}
	
}
