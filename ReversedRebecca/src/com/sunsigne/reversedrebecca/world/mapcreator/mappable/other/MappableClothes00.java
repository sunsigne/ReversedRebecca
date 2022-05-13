package com.sunsigne.reversedrebecca.world.mapcreator.mappable.other;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.other.decoration.ResizedDecorationObject;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableClothes00 implements Mappable {

	private MappableClothes00() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableClothes00();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new ResizedDecorationObject(x, y, 2 * Size.M, 2 * Size.M, "clothes_00");
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 253, 0, 255 };
		return rgb;
	}

}
