package com.sunsigne.reversedrebecca.world.mapcreator.mappable.livings;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player.Player;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappablePlayer implements Mappable {

	static {
		new MapCreator().getList().addObject(new MappablePlayer());
	}
	
	////////// MAPPABLE ////////////
	
	@Override
	public GameObject createObject(int x, int y) {
		return new Player(x, y);
	}
	
	@Override
	public int[] rgbCode() {
		int[] rgb = {255, 0, 0};
		return rgb;
	}	
}
