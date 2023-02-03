package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.door.white;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.Wall.COLOR;
import com.sunsigne.reversedrebecca.object.puzzler.door.NullDoorObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableWhiteNullDoor implements Mappable {

	private MappableWhiteNullDoor() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableWhiteNullDoor();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new NullDoorObject(COLOR.WHITE, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 1, 235, 0 };
		return rgb;
	}
	
}
