package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler;

import com.sunsigne.reversedrebecca.object.Wall.COLOR;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject.DEV_LVL;
import com.sunsigne.reversedrebecca.object.puzzler.hole.upward.HoleUpwardObject;
import com.sunsigne.reversedrebecca.object.puzzler.hole.upward.NullHoleUpwardObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableUpwardHole implements MappablePuzzler {

	private MappableUpwardHole() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableUpwardHole();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public PuzzlerObject getPuzzlerObject(DEV_LVL devDifficulty, LVL difficulty, COLOR color, DIRECTION facing,
			boolean horizontal, int x, int y) {

		if (devDifficulty == null && difficulty == LVL.NULL)
			return new NullHoleUpwardObject(facing, x, y);

		else if (devDifficulty != null)
			return new HoleUpwardObject(devDifficulty, facing, x, y);

		else
			return new HoleUpwardObject(difficulty, facing, x, y);
	}

	@Override
	public int[] rgbCode() {
		// green = 255 to X
		// blue = 0 to 10
		int[] rgb = { 5, 256, -1 };
		return rgb;
	}

}
