package com.sunsigne.reversedrebecca.physic;

import com.sunsigne.reversedrebecca.pattern.list.GameLimitedList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.physic.laws.PhysicLaw;

public class PhysicList {

	////////// MAP OR LIST ////////////
	
	private static GameLimitedList<PhysicLaw> list = new GameLimitedList<>(LISTTYPE.ARRAY);

	public static GameLimitedList<PhysicLaw> getList() {
		return list;
	}
}
