package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.door;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.puzzler.door.DoorObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappablePurpleDoor implements Mappable {

	private MappablePurpleDoor() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappablePurpleDoor();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new DoorObject(LVL.PURPLE, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 0, 255, 6 };
		return rgb;
	}
	
}
