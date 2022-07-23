package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.chest;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.puzzler.chest.ChestObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableChest05 implements Mappable {

	private MappableChest05() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableChest05();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new ChestObject(5, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 5, 128, 255 };
		return rgb;
	}

}
