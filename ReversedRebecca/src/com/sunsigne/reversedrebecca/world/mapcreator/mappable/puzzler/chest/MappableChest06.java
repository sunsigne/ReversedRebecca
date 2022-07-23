package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.chest;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.puzzler.chest.ChestObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableChest06 implements Mappable {

	private MappableChest06() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableChest06();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new ChestObject(6, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 6, 128, 255 };
		return rgb;
	}

}
