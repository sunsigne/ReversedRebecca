package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.door;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.interactive.puzzler.NullDoorObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableNullDoor implements Mappable {

	private MappableNullDoor() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableNullDoor();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new NullDoorObject(x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 0, 255, 0 };
		return rgb;
	}
	
}
