package com.sunsigne.reversedrebecca.world.mapcreator.mappable.other;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.other.AnimatedDecorationObject;
import com.sunsigne.reversedrebecca.system.Size;
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
		return new AnimatedDecorationObject(x, y, Size.M, 2 * Size.M, "bath", 90, 3, false);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 250, 0, 255 };
		return rgb;
	}

}
