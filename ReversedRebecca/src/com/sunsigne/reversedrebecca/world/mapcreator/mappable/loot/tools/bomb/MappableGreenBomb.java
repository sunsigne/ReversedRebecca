package com.sunsigne.reversedrebecca.world.mapcreator.mappable.loot.tools.bomb;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.loot.tools.BombToolObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableGreenBomb implements Mappable {

	static {
		new MapCreator().getList().addObject(new MappableGreenBomb());
	}
	
	////////// MAPPABLE ////////////
	
	@Override
	public GameObject createObject(int x, int y) {
		return new BombToolObject(LVL.GREEN, x, y);
	}
	
	@Override
	public int[] rgbCode() {
		int[] rgb = {1, 1, 255};
		return rgb;
	}	
}
