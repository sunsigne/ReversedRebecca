package com.sunsigne.reversedrebecca.world.mapcreator.mappable.other;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.other.DecorationObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableGlass implements Mappable {

	private MappableGlass() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableGlass();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new DecorationObject(x, y, "glass");
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 251, 0, 254 };
		return rgb;
	}

}
