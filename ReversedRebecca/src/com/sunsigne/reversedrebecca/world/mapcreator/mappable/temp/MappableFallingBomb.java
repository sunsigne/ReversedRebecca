package com.sunsigne.reversedrebecca.world.mapcreator.mappable.temp;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.Wall;
import com.sunsigne.reversedrebecca.object.hostile.FallingBomb;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableFallingBomb implements Mappable {

	private MappableFallingBomb() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableFallingBomb();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new FallingBomb(x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 255, 128, 0 };
		return rgb;
	}

}
