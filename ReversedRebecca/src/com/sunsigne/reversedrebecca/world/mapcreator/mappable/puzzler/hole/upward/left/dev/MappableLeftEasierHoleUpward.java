package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.hole.upward.left.dev;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject.DEV_LVL;
import com.sunsigne.reversedrebecca.object.puzzler.hole.upward.HoleUpwardObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableLeftEasierHoleUpward implements Mappable {

	private MappableLeftEasierHoleUpward() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableLeftEasierHoleUpward();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new HoleUpwardObject(DEV_LVL.EASIER, DIRECTION.LEFT, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 5, 254, 8 };
		return rgb;
	}

}
