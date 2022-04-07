package com.sunsigne.reversedrebecca.world.mapcreator.mappable.extrabehavior.interactive;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.InteractiveObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableInteractive25 implements Mappable {

	private MappableInteractive25() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableInteractive25();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new InteractiveObject("OBJECT-25", x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 25, 255, 255 };
		return rgb;
	}

}