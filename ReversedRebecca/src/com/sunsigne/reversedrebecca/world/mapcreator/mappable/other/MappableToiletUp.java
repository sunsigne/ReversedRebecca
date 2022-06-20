package com.sunsigne.reversedrebecca.world.mapcreator.mappable.other;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.other.DecorationObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableToiletUp implements Mappable {

	private MappableToiletUp() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableToiletUp();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new DecorationObject(x, y, "toilet_up");
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 254, 0, 247 };
		return rgb;
	}

}
