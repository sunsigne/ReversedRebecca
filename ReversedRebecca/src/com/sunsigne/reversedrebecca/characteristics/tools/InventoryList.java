package com.sunsigne.reversedrebecca.characteristics.tools;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import com.sunsigne.reversedrebecca.characteristics.Characteristic;
import com.sunsigne.reversedrebecca.pattern.list.GameLimitedList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;

public class InventoryList extends Characteristic {

	////////// MAP OR LIST ////////////

	protected static GameLimitedList<BufferedImage> list = new GameLimitedList<>(LISTTYPE.ARRAY);

	protected static Map<BufferedImage, BufferedImage> map = new HashMap<>();

	////////// CHARACTERISTICS ////////////

	private static InventoryList instance = new InventoryList();

	@Override
	protected Characteristic getInstance() {
		return instance;
	}

	@Override
	public void reset() {
		list.clear();
		map.clear();
	}

}
