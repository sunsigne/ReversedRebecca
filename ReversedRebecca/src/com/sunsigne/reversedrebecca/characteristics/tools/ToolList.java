package com.sunsigne.reversedrebecca.characteristics.tools;

import com.sunsigne.reversedrebecca.characteristics.Characteristic;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.pattern.list.GameLimitedList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;

public class ToolList extends Characteristic {

	////////// MAP OR LIST ////////////

	private static GameLimitedList<ToolPlayer> list = new GameLimitedList<>(LISTTYPE.ARRAY);

	public static GameLimitedList<ToolPlayer> getList() {
		return list;
	}

	////////// CHARACTERISTICS ////////////

	private static ToolList instance = new ToolList();

	@Override
	protected Characteristic getInstance() {
		return instance;
	}

	@Override
	public void reset() {
		getList().getList().forEach(tempTool -> {
			tempTool.setStartDifficulty(null);
			tempTool.setMaxDifficulty(LVL.NULL);
			tempTool.setCriticalChance(-1);
		});
	}

}
