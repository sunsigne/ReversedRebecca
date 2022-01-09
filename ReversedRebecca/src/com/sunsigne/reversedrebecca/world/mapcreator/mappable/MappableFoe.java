package com.sunsigne.reversedrebecca.world.mapcreator.mappable;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.foe.Foe;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;

public class MappableFoe implements Mappable {

	static {
		MapCreator.mappable_list.addObject(new MappableFoe());
	}
	
	////////// MAPPABLE ////////////
	
	@Override
	public GameObject getObject() {
		return new Foe(0, 0);
	}
	
	@Override
	public int[] rgbCode() {
		int[] rgb = {255, 255, 0};
		return rgb;
	}	
}
