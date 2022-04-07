package com.sunsigne.reversedrebecca.world.mapcreator.mappable.extrabehavior.interactive;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.InteractiveObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableInteractive11 implements Mappable {

	private MappableInteractive11() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableInteractive11();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new InteractiveObject("OBJECT-11", x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 11, 255, 255 };
		return rgb;
	}

}
