package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.hole.doward.down;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.puzzler.hole.downward.NullHoleObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableDownNullHole implements Mappable {

	private MappableDownNullHole() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableDownNullHole();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new NullHoleObject(DIRECTION.DOWN, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 4, 251, 0 };
		return rgb;
	}

}
