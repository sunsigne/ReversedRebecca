package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.hole.dev;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject.DEV_LVL;
import com.sunsigne.reversedrebecca.object.puzzler.hole.HoleObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableEasierUpHole implements Mappable {

	private MappableEasierUpHole() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableEasierUpHole();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new HoleObject(DEV_LVL.EASIER, DIRECTION.UP, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 4, 252, 8 };
		return rgb;
	}

}
