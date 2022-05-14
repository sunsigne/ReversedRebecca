package com.sunsigne.reversedrebecca.world.mapcreator.mappable.other;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.other.DecorationObject;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableBathHair implements Mappable {

	private MappableBathHair() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableBathHair();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new DecorationObject(x, y, Size.M, 2 * Size.M, "bath_hair");
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 250, 0, 253 };
		return rgb;
	}

}
