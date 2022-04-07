package com.sunsigne.reversedrebecca.world.mapcreator.mappable.extrabehavior.interactive;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.InteractiveObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableInteractive44 implements Mappable {

	private MappableInteractive44() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableInteractive44();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new InteractiveObject("OBJECT-44", x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 44, 255, 255 };
		return rgb;
	}

}