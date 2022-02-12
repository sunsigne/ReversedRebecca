package com.sunsigne.reversedrebecca.pattern.list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GameList<T> {

	public GameList(LISTTYPE listType) {
		this.listType = listType;
	}

	////////// MAP OR LIST ////////////

	private ArrayList<T> array_list = new ArrayList<>();
	private LinkedList<T> linked_list = new LinkedList<>();

	private LISTTYPE listType;
	
	public List<T> getList() {
		switch (listType) {
		case ARRAY:
			return array_list;
		case LINKED:
			return linked_list;
		}
		// this should theorically not occurs, but it does ... Better not return null
		return array_list;
	}	

	public boolean cointainsObject(T object) {
		if (object == null)
			return false;

		return getList().contains(object);
	}

	public void addObject(T object) {
		if (object == null || cointainsObject(object))
			return;

		getList().add(object);
	}

	public void removeObject(T object) {
		if (object == null || !cointainsObject(object))
			return;

		getList().remove(object);
	}
	
	public void clear() {
		getList().clear();
	}

}
