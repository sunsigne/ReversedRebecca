package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.chest;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.puzzler.chest.ChestObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableChest01 implements Mappable {

	private MappableChest01() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableChest01();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new ChestObject(1, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 1, 128, 255 };
		return rgb;
	}

}
