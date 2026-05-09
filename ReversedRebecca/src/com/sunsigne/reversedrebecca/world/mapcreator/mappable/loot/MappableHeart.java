package com.sunsigne.reversedrebecca.world.mapcreator.mappable.loot;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.loot.HeartLoot;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.MappableComplexe;

public class MappableHeart implements MappableComplexe {

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
	public GameObject createObject(int red, int green, int blue, int x, int y) {
		return new HeartLoot(x, y, green == 1);
	}

	@Override
	public int[] rgbCode() {
		// green = 0 or 1
		int[] rgb = { 0, -1, 255 };
		return rgb;
	}

	@Override
	public boolean isValidGreen(int green) {
		return green <= 1;
	}

}
