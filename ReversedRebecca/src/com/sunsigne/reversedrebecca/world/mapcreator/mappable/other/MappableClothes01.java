package com.sunsigne.reversedrebecca.world.mapcreator.mappable.other;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.other.decoration.LargeDecorationObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableClothes01 implements Mappable {

	private MappableClothes01() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableClothes01();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new LargeDecorationObject(x, y, "clothes_01");
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 253, 0, 254 };
		return rgb;
	}

}
