package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.door.white.dev;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.Wall.COLOR;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject.DEV_LVL;
import com.sunsigne.reversedrebecca.object.puzzler.door.DoorObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableWhiteHardestDoor implements Mappable {

	private MappableWhiteHardestDoor() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableWhiteHardestDoor();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new DoorObject(DEV_LVL.HARDEST, COLOR.WHITE, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 1, 235, 10 };
		return rgb;
	}

}
