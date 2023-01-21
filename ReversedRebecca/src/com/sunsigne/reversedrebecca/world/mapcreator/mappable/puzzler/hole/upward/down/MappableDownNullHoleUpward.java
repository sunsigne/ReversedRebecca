package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.hole.upward.down;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.puzzler.hole.upward.NullHoleUpwardObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableDownNullHoleUpward implements Mappable {

	private MappableDownNullHoleUpward() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableDownNullHoleUpward();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new NullHoleUpwardObject(DIRECTION.DOWN, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 5, 251, 0 };
		return rgb;
	}

}
