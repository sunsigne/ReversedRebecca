package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.hole.upward.down.dev;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject.DEV_LVL;
import com.sunsigne.reversedrebecca.object.puzzler.hole.upward.HoleUpwardObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableDownHardestHoleUpward implements Mappable {

	private MappableDownHardestHoleUpward() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableDownHardestHoleUpward();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new HoleUpwardObject(DEV_LVL.HARDEST, DIRECTION.DOWN, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 5, 251, 10 };
		return rgb;
	}

}
