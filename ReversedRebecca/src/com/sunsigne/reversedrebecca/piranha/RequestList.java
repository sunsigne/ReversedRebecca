package com.sunsigne.reversedrebecca.piranha;

import com.sunsigne.reversedrebecca.pattern.list.GameLimitedList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.piranha.request.Request;

public class RequestList {
	
	////////// MAP OR LIST ////////////
	
	private static GameLimitedList<Request> list = new GameLimitedList<>(LISTTYPE.ARRAY);
	
	public static GameLimitedList<Request> getList() {
		return list;
	}

}
