package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.hole.doward.left;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.puzzler.hole.downward.HoleObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableLeftRedHole implements Mappable {

	private MappableLeftRedHole() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableLeftRedHole();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new HoleObject(LVL.RED, DIRECTION.LEFT, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 4, 254, 5 };
		return rgb;
	}

}
