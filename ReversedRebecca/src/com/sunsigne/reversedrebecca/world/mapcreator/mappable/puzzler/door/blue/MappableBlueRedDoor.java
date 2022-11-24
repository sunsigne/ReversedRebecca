package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.door.blue;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.Wall.COLOR;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.puzzler.door.DoorObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableBlueRedDoor implements Mappable {

	private MappableBlueRedDoor() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableBlueRedDoor();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new DoorObject(LVL.RED, COLOR.BLUE, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 1, 255, 5 };
		return rgb;
	}
	
}
