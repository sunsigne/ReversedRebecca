package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.hole.upward.left;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.puzzler.hole.upward.HoleUpwardObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableLeftPurpleHoleUpward implements Mappable {

	private MappableLeftPurpleHoleUpward() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableLeftPurpleHoleUpward();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new HoleUpwardObject(LVL.PURPLE, DIRECTION.LEFT, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 5, 254, 6 };
		return rgb;
	}

}
