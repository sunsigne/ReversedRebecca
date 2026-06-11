package com.sunsigne.reversedrebecca.characteristics.tools;

import java.awt.image.BufferedImage;
import java.util.LinkedHashMap;

import com.sunsigne.reversedrebecca.characteristics.Characteristic;

public class InventoryList extends Characteristic {

	////////// MAP OR LIST ////////////

	protected static LinkedHashMap<Integer, BufferedImage> map = new LinkedHashMap<>();

	////////// CHARACTERISTICS ////////////

	private static InventoryList instance = new InventoryList();

	@Override
	protected Characteristic getInstance() {
		return instance;
	}

	@Override
	public void reset() {
		map.clear();
	}

}
