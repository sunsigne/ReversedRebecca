package com.sunsigne.reversedrebecca.world.mapcreator.mappable.interactive;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.InteractiveObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableInteractive08 implements Mappable {

	private MappableInteractive08() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableInteractive08();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new InteractiveObject("OBJECT-08", x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 8, 255, 255 };
		return rgb;
	}

}
