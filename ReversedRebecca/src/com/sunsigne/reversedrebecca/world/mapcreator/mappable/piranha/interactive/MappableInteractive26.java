package com.sunsigne.reversedrebecca.world.mapcreator.mappable.piranha.interactive;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.piranha.InteractiveObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableInteractive26 implements Mappable {

	private MappableInteractive26() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableInteractive26();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new InteractiveObject("OBJ-26", x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 26, 255, 255 };
		return rgb;
	}

}
