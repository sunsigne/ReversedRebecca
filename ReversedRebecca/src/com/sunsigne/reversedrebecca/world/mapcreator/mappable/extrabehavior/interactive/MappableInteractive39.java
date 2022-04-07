package com.sunsigne.reversedrebecca.world.mapcreator.mappable.extrabehavior.interactive;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.InteractiveObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableInteractive39 implements Mappable {

	private MappableInteractive39() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableInteractive39();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new InteractiveObject("OBJECT-39", x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 39, 255, 255 };
		return rgb;
	}

}
