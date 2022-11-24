package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.door.green;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.Wall.COLOR;
import com.sunsigne.reversedrebecca.object.puzzler.door.NullDoorObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableGreenNullDoor implements Mappable {

	private MappableGreenNullDoor() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableGreenNullDoor();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new NullDoorObject(COLOR.GREEN, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 1, 245, 0 };
		return rgb;
	}
	
}
