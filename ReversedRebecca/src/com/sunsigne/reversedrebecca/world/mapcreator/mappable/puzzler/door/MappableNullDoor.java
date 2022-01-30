package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.door;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.puzzler.DoorObject;
import com.sunsigne.reversedrebecca.ressources.DIFFICULTY;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableNullDoor implements Mappable {

	static {
		new MapCreator().getList().addObject(new MappableNullDoor());
	}
	
	////////// MAPPABLE ////////////
	
	@Override
	public GameObject createObject(int x, int y) {
		return new DoorObject(DIFFICULTY.NULL, x, y);
	}
	
	@Override
	public int[] rgbCode() {
		int[] rgb = {0, 255, 0};
		return rgb;
	}	
}
