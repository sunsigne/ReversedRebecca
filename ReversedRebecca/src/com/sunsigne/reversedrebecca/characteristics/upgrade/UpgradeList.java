package com.sunsigne.reversedrebecca.characteristics.upgrade;

import com.sunsigne.reversedrebecca.characteristics.Characteristic;
import com.sunsigne.reversedrebecca.pattern.list.GameLimitedList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;

public class UpgradeList extends Characteristic {

	////////// MAP OR LIST ////////////

	private static GameLimitedList<UpgradePlayer> list = new GameLimitedList<>(LISTTYPE.ARRAY);

	public static GameLimitedList<UpgradePlayer> getList() {
		return list;
	}

	////////// CHARACTERISTICS ////////////

	private static UpgradeList instance = new UpgradeList();

	@Override
	protected Characteristic getInstance() {
		return instance;
	}

	@Override
	public void reset() {
		getList().getList().forEach(tempUpgrade -> {
			tempUpgrade.setValue(false);
		});
	}

}
