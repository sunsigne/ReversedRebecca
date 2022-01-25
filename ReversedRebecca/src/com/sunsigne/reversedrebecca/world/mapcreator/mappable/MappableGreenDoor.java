package com.sunsigne.reversedrebecca.world.mapcreator.mappable;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.Wall;
import com.sunsigne.reversedrebecca.object.puzzler.DoorObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;

public class MappableGreenDoor implements Mappable {

	static {
		new MapCreator().getList().addObject(new MappableGreenDoor());
	}
	
	////////// MAPPABLE ////////////
	
	@Override
	public GameObject createObject(int x, int y) {
		return new DoorObject(2, x, y);
	}
	
	@Override
	public int[] rgbCode() {
		int[] rgb = {0, 255, 2};
		return rgb;
	}	
}
