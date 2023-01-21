package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.hole.doward.down;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.puzzler.hole.downward.HoleObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableDownOrangeHole implements Mappable {

	private MappableDownOrangeHole() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableDownOrangeHole();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new HoleObject(LVL.ORANGE, DIRECTION.DOWN, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 4, 251, 4 };
		return rgb;
	}

}
