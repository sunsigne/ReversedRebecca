package com.sunsigne.reversedrebecca.world.mapcreator.mappable;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player.Player;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;

public class MappablePlayer implements Mappable {

	static {
		MapCreator.mappable_list.addObject(new MappablePlayer());
	}
	
	////////// MAPPABLE ////////////
	
	@Override
	public GameObject getObject() {
		return new Player(0, 0);
	}
	
	@Override
	public int[] rgbCode() {
		int[] rgb = {255, 0, 0};
		return rgb;
	}	
}
