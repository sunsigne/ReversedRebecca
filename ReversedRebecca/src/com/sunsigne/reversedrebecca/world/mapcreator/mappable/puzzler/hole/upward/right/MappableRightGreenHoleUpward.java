package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.hole.upward.right;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.puzzler.hole.upward.HoleUpwardObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableRightGreenHoleUpward implements Mappable {

	private MappableRightGreenHoleUpward() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableRightGreenHoleUpward();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new HoleUpwardObject(LVL.GREEN, DIRECTION.RIGHT, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 5, 253, 2 };
		return rgb;
	}

}
