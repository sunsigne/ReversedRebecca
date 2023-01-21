package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.hole.doward.right.dev;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject.DEV_LVL;
import com.sunsigne.reversedrebecca.object.puzzler.hole.downward.HoleObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableRightHarderHole implements Mappable {

	private MappableRightHarderHole() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableRightHarderHole();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new HoleObject(DEV_LVL.HARDER, DIRECTION.RIGHT, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 4, 253, 9 };
		return rgb;
	}

}
