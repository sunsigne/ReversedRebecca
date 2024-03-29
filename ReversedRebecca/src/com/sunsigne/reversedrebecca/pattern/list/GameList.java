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
			while (array_list.remove(null));
			return array_list;
		case LINKED:
			while (linked_list.remove(null));
			if(linked_list.size() < 0)
				return array_list;
			return linked_list;
		}
		return null;
	}

	public boolean containsObject(T object) {
		if (object == null)
			return false;

		return getList().contains(object);
	}

	public void addObject(T object) {
		if (object == null || containsObject(object))
			return;

		getList().add(object);
	}

	public void removeObject(T object) {
		if (object == null || !containsObject(object))
			return;

		getList().remove(object);
	}

	public void clear() {
		getList().clear();
	}

}
