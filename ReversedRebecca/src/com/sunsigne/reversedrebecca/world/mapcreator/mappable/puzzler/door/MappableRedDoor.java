package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.door;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.interactive.puzzler.DoorObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableRedDoor implements Mappable {

	private MappableRedDoor() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableRedDoor();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new DoorObject(LVL.RED, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 0, 255, 5 };
		return rgb;
	}
	
}
