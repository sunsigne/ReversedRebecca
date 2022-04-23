package com.sunsigne.reversedrebecca.world.mapcreator.mappable.piranha.interactive;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.piranha.InteractiveObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableInteractive31 implements Mappable {

	private MappableInteractive31() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableInteractive31();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new InteractiveObject("OBJ-31", x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 31, 255, 255 };
		return rgb;
	}

}
