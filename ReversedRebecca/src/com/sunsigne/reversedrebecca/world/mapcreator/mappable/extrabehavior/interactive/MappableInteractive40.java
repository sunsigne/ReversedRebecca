package com.sunsigne.reversedrebecca.world.mapcreator.mappable.extrabehavior.interactive;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.InteractiveObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableInteractive40 implements Mappable {

	private MappableInteractive40() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableInteractive40();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new InteractiveObject("OBJ-40", x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 40, 255, 255 };
		return rgb;
	}

}
