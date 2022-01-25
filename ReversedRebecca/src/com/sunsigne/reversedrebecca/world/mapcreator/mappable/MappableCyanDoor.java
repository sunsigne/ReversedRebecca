package com.sunsigne.reversedrebecca.world.mapcreator.mappable;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.Wall;
import com.sunsigne.reversedrebecca.object.puzzler.DoorObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;

public class MappableCyanDoor implements Mappable {

	static {
		new MapCreator().getList().addObject(new MappableCyanDoor());
	}
	
	////////// MAPPABLE ////////////
	
	@Override
	public GameObject createObject(int x, int y) {
		return new DoorObject(1, x, y);
	}
	
	@Override
	public int[] rgbCode() {
		int[] rgb = {0, 255, 1};
		return rgb;
	}	
}
