package com.sunsigne.reversedrebecca.world.mapcreator.mappable.other;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.other.Clothes;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableClothes implements Mappable {

	private MappableClothes() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableClothes();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new Clothes(x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 252, 0, 255 };
		return rgb;
	}

}
