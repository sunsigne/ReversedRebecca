package com.sunsigne.reversedrebecca.world.mapcreator.mappable.other;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.other.AnimatedDecorationObject;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableBathEmptying implements Mappable {

	private MappableBathEmptying() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableBathEmptying();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new AnimatedDecorationObject(x, y, Size.M, 2 * Size.M, "bath_emptying", 70, 3, false);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 250, 0, 254 };
		return rgb;
	}

}
