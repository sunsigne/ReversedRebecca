package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.door;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.interactive.puzzler.NullDoorObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableNullDoor implements Mappable {

	static {
		new MapCreator().getList().addObject(new MappableNullDoor());
	}
	
	////////// MAPPABLE ////////////
	
	@Override
	public GameObject createObject(int x, int y) {
		return new NullDoorObject(x, y);
	}
	
	@Override
	public int[] rgbCode() {
		int[] rgb = {0, 255, 0};
		return rgb;
	}	
}
