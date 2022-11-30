package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.door.green.dev;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.Wall.COLOR;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject.DEV_LVL;
import com.sunsigne.reversedrebecca.object.puzzler.door.DoorObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableGreenEasiestDoor implements Mappable {

	private MappableGreenEasiestDoor() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableGreenEasiestDoor();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new DoorObject(DEV_LVL.EASIEST, COLOR.GREEN, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 1, 245, 7 };
		return rgb;
	}

}