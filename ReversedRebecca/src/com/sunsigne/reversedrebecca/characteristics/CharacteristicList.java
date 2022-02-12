package com.sunsigne.reversedrebecca.characteristics;

import com.sunsigne.reversedrebecca.pattern.list.GameLimitedList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;

public class CharacteristicList {

	////////// MAP OR LIST ////////////

	private static GameLimitedList<Characteristic> list = new GameLimitedList<>(LISTTYPE.ARRAY);

	public static GameLimitedList<Characteristic> getList() {
		return list;
	}

	////////// CHARACTERISTICS ////////////

	public void reset() {
		for (Characteristic tempCharacteristic : list.getList()) {
			tempCharacteristic.reset();
		}
	}

}
