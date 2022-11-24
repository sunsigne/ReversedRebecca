package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.door.blue;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.Wall.COLOR;
import com.sunsigne.reversedrebecca.object.puzzler.door.NullDoorObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableBlueNullDoor implements Mappable {

	private MappableBlueNullDoor() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableBlueNullDoor();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new NullDoorObject(COLOR.BLUE, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 1, 255, 0 };
		return rgb;
	}
	
}
