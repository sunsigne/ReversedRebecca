package com.sunsigne.reversedrebecca.world.mapcreator.mappable.piranha.interactive;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.piranha.InteractiveObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableInteractive14 implements Mappable {

	private MappableInteractive14() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableInteractive14();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new InteractiveObject("OBJ-14", x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 14, 255, 255 };
		return rgb;
	}

}
