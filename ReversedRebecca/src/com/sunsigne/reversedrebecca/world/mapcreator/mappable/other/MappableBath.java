package com.sunsigne.reversedrebecca.world.mapcreator.mappable.other;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.other.decoration.Bath;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableBath implements Mappable {

	private MappableBath() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableBath();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new Bath(x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 250, 0, 255 };
		return rgb;
	}

}
