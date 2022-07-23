package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.chest;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.puzzler.chest.ChestObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableChest07 implements Mappable {

	private MappableChest07() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableChest07();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new ChestObject(7, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 7, 128, 255 };
		return rgb;
	}

}
