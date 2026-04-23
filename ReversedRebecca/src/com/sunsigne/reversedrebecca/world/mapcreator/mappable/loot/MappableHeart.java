package com.sunsigne.reversedrebecca.world.mapcreator.mappable.loot;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.loot.HeartLoot;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableHeart implements Mappable {

	private MappableHeart() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableHeart();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new HeartLoot(x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 0, 0, 255 };
		return rgb;
	}

}
