package com.sunsigne.reversedrebecca.world.mapcreator.mappable.other;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.other.DecorationObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableClothes02 implements Mappable {

	private MappableClothes02() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableClothes02();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new DecorationObject(x, y, "clothes_02");
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 253, 0, 253 };
		return rgb;
	}

}
