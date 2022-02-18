package com.sunsigne.reversedrebecca.world.mapcreator.mappable;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.PathPointObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;

public class MappablePathPoint implements Mappable {

	private MappablePathPoint() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappablePathPoint();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new PathPointObject(x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 48, 48, 48 };
		return rgb;
	}
	
}
