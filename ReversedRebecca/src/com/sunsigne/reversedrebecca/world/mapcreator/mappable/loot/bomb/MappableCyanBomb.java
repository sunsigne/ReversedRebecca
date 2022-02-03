package com.sunsigne.reversedrebecca.world.mapcreator.mappable.loot.bomb;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.loot.BombToolObject;
import com.sunsigne.reversedrebecca.ressources.DIFFICULTY;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableCyanBomb implements Mappable {

	static {
		new MapCreator().getList().addObject(new MappableCyanBomb());
	}
	
	////////// MAPPABLE ////////////
	
	@Override
	public GameObject createObject(int x, int y) {
		return new BombToolObject(DIFFICULTY.CYAN, x, y);
	}
	
	@Override
	public int[] rgbCode() {
		int[] rgb = {1, 0, 255};
		return rgb;
	}	
}
