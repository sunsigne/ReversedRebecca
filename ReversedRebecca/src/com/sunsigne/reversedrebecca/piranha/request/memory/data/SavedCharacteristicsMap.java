package com.sunsigne.reversedrebecca.piranha.request.memory.data;

import java.util.HashMap;

public class SavedCharacteristicsMap {

	////////// MAP OR LIST ////////////

	private static HashMap<Object, SavedCharacteristic> map = new HashMap<>();

	public static HashMap<Object, SavedCharacteristic> getMap() {
		return map;
	}

}
