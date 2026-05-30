package com.sunsigne.reversedrebecca.characteristics.tools;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.characteristics.Characteristic;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;

public class InventoryList extends Characteristic {

	////////// MAP OR LIST ////////////

	protected static GameList<BufferedImage> list = new GameList<>(LISTTYPE.ARRAY);

	////////// CHARACTERISTICS ////////////

	private static InventoryList instance = new InventoryList();

	@Override
	protected Characteristic getInstance() {
		return instance;
	}

	@Override
	public void reset() {
		list.clear();
	}

}
