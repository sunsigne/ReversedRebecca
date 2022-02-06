package com.sunsigne.reversedrebecca.world.mapcreator.mappable;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.Wall;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;

public class MappableWall implements Mappable {

	private MappableWall() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableWall();

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
		int[] rgb = { 255, 255, 255 };
		return rgb;
	}

}
