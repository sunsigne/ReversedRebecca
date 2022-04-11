package com.sunsigne.reversedrebecca.piranha.request;

import com.sunsigne.reversedrebecca.pattern.list.GameLimitedList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;

public class RequestList {
	
	////////// MAP OR LIST ////////////
	
	private static GameLimitedList<Request> list = new GameLimitedList<>(LISTTYPE.ARRAY);
	
	public static GameLimitedList<Request> getList() {
		return list;
	}

}
