package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.hole.right;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.puzzler.hole.HoleObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableRightPurpleHole implements Mappable {

	private MappableRightPurpleHole() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableRightPurpleHole();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new HoleObject(LVL.PURPLE, DIRECTION.RIGHT, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 4, 253, 6 };
		return rgb;
	}

}