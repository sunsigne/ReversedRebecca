package com.sunsigne.reversedrebecca.world.mapcreator.mappable;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.Wall;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;

public class MappableWallDecoration implements Mappable {

	private MappableWallDecoration() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableWallDecoration();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new Wall(x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 128, 128, 255 };
		return rgb;
	}

}
