package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.door;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.interactive.puzzler.DoorObject;
import com.sunsigne.reversedrebecca.ressources.DIFFICULTY;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableGreenDoor implements Mappable {

	static {
		new MapCreator().getList().addObject(new MappableGreenDoor());
	}
	
	////////// MAPPABLE ////////////
	
	@Override
	public GameObject createObject(int x, int y) {
		return new DoorObject(DIFFICULTY.GREEN, x, y);
	}
	
	@Override
	public int[] rgbCode() {
		int[] rgb = {0, 255, 2};
		return rgb;
	}	
}
