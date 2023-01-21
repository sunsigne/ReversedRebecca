package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.hole.doward.up;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.puzzler.hole.downward.HoleObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableUpCyanHole implements Mappable {

	private MappableUpCyanHole() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableUpCyanHole();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new HoleObject(LVL.CYAN, DIRECTION.UP, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 4, 252, 1 };
		return rgb;
	}

}
