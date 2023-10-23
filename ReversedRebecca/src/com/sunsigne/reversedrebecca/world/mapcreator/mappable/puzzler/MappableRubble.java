package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler;

import com.sunsigne.reversedrebecca.object.Wall.COLOR;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject.DEV_LVL;
import com.sunsigne.reversedrebecca.object.puzzler.rubble.RubbleObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableRubble implements MappablePuzzler {

	private MappableRubble() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableRubble();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public PuzzlerObject getPuzzlerObject(DEV_LVL devDifficulty, LVL difficulty, COLOR color, DIRECTION facing,
			boolean horizontal, int x, int y) {

		if (devDifficulty == null && difficulty == LVL.NULL)
			return null;

		else if (devDifficulty != null)
			return new RubbleObject(devDifficulty, color, horizontal, x, y);

		else
			return new RubbleObject(difficulty, color, horizontal, x, y);
	}
	
	public static int getRed() {
		return mappable.getRedCode();
	}
	
	@Override
	public int[] rgbCode() {
		// green = 255 to X
		// blue = 0 to 10
		int[] rgb = { 2, 256, -1 };
		return rgb;
	}

}
