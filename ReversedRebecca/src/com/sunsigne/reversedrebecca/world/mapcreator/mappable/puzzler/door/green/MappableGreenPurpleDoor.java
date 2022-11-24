package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.door.green;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.Wall.COLOR;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.puzzler.door.DoorObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableGreenPurpleDoor implements Mappable {

	private MappableGreenPurpleDoor() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableGreenPurpleDoor();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new DoorObject(LVL.PURPLE, COLOR.GREEN, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 1, 245, 6 };
		return rgb;
	}
	
}
