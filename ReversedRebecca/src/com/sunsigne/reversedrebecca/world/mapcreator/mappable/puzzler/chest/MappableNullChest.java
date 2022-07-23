package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.chest;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.puzzler.chest.NullChestObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableNullChest implements Mappable {

	private MappableNullChest() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableNullChest();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new NullChestObject(x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 0, 128, 255 };
		return rgb;
	}

}
