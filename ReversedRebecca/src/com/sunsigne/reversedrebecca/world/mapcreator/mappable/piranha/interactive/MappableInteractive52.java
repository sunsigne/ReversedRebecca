package com.sunsigne.reversedrebecca.world.mapcreator.mappable.piranha.interactive;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.piranha.InteractiveObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableInteractive52 implements Mappable {

	private MappableInteractive52() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableInteractive52();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new InteractiveObject("OBJ-52", x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 52, 255, 255 };
		return rgb;
	}

}
