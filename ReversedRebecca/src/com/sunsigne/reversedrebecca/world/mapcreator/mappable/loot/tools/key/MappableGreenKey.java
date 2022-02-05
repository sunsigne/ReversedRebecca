package com.sunsigne.reversedrebecca.world.mapcreator.mappable.loot.tools.key;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.loot.tools.KeyToolObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableGreenKey implements Mappable {

	static {
		new MapCreator().getList().addObject(new MappableGreenKey());
	}
	
	////////// MAPPABLE ////////////
	
	@Override
	public GameObject createObject(int x, int y) {
		return new KeyToolObject(LVL.GREEN, x, y);
	}
	
	@Override
	public int[] rgbCode() {
		int[] rgb = {0, 1, 255};
		return rgb;
	}	
}
