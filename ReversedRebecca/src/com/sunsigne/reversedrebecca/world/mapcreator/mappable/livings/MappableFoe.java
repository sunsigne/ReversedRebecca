package com.sunsigne.reversedrebecca.world.mapcreator.mappable.livings;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.foe.Foe;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableFoe implements Mappable {

	static {
		new MapCreator().getList().addObject(new MappableFoe());
	}
	
	////////// MAPPABLE ////////////
	
	@Override
	public GameObject createObject(int x, int y) {
		return new Foe(x, y);
	}
	
	@Override
	public int[] rgbCode() {
		int[] rgb = {255, 255, 0};
		return rgb;
	}	
}
