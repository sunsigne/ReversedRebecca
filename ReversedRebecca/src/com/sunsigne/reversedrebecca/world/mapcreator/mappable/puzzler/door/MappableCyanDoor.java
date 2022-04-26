package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.door;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.puzzler.door.DoorObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableCyanDoor implements Mappable {

	private MappableCyanDoor() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableCyanDoor();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new DoorObject(LVL.CYAN, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 1, 255, 1 };
		return rgb;
	}
	
}
