package com.sunsigne.reversedrebecca.object.extrabehaviors.livings.npc.actions;

import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.npc.actions.action.NPCAction;
import com.sunsigne.reversedrebecca.pattern.list.GameLimitedList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;

public class ActionList {

	////////// MAP OR LIST ////////////

	private static GameLimitedList<NPCAction> list = new GameLimitedList<>(LISTTYPE.ARRAY);

	public static GameLimitedList<NPCAction> getList() {
		return list;
	}

}
