package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.door.dev;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.DevDifficulty.DEV_LVL;
import com.sunsigne.reversedrebecca.object.puzzler.door.dev.DevDoorObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableEasierDoor implements Mappable {

	private MappableEasierDoor() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableEasierDoor();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new DevDoorObject(DEV_LVL.EASIER, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 1, 255, 8 };
		return rgb;
	}

}
