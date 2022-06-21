package com.sunsigne.reversedrebecca.world.mapcreator.mappable.other;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.other.DecorationObject;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableGlass00 implements Mappable {

	private MappableGlass00() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableGlass00();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new DecorationObject(x, y, Size.M, Size.M, "glass_00");
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 251, 0, 255 };
		return rgb;
	}

}
