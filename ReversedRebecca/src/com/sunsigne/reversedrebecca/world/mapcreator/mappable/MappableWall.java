package com.sunsigne.reversedrebecca.world.mapcreator.mappable;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.Wall;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;

public class MappableWall implements Mappable {

	static {
		MapCreator.mappable_list.addObject(new MappableWall());
	}
	
	////////// MAPPABLE ////////////
	
	@Override
	public GameObject getObject() {
		return new Wall(0, 0);
	}
	
	@Override
	public int[] rgbCode() {
		int[] rgb = {255, 255, 255};
		return rgb;
	}	
}
