package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.hole.doward.left.dev;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject.DEV_LVL;
import com.sunsigne.reversedrebecca.object.puzzler.hole.downward.HoleObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableLeftHardestHole implements Mappable {

	private MappableLeftHardestHole() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableLeftHardestHole();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new HoleObject(DEV_LVL.HARDEST, DIRECTION.LEFT, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 4, 254, 10 };
		return rgb;
	}

}
