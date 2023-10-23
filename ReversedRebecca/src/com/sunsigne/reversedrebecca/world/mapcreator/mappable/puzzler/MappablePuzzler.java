package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.Wall.COLOR;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject.DEV_LVL;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.MappableComplexe;

public interface MappablePuzzler extends MappableComplexe {

	////////// MAPPABLE ////////////

	public PuzzlerObject getPuzzlerObject(DEV_LVL devDifficulty, LVL difficulty, COLOR color, DIRECTION facing,
			boolean horizontal, int x, int y);

	@Override
	public default GameObject createObject(int red, int green, int blue, int x, int y) {
		LVL difficulty = LVL.NULL;
		DEV_LVL dev_difficulty = getDevDifficulty(blue);
		COLOR color = getColor(green);
		DIRECTION facing = getFacing(green);
		boolean horizontal = green % 10 != 0;

		while (blue > 0) {
			blue--;
			difficulty = difficulty.getNext();
		}

		return getPuzzlerObject(dev_difficulty, difficulty, color, facing, horizontal, x, y);
	}

	public default DEV_LVL getDevDifficulty(int blue) {
		if (blue == 7)
			return DEV_LVL.EASIEST;
		if (blue == 8)
			return DEV_LVL.EASIER;
		if (blue == 9)
			return DEV_LVL.HARDER;
		if (blue == 10)
			return DEV_LVL.HARDEST;

		return null;
	}

	public default COLOR getColor(int green) {
		COLOR color = null;
		COLOR tempColor = null;

		while (color == null) {
			tempColor = nextColor(tempColor);

			if (250 <= green && green <= 255)
				color = tempColor;

			green = green + 10;
		}

		return color;
	}

	public default DIRECTION getFacing(int green) {
		int facing = 254 - green;

		if (facing == DIRECTION.LEFT.getNum())
			return DIRECTION.LEFT;
		if (facing == DIRECTION.RIGHT.getNum())
			return DIRECTION.RIGHT;
		if (facing == DIRECTION.UP.getNum())
			return DIRECTION.UP;
		if (facing == DIRECTION.DOWN.getNum())
			return DIRECTION.DOWN;

		return DIRECTION.NULL;
	}

	public default COLOR nextColor(COLOR tempColor) {
		if (tempColor == null)
			return COLOR.BLUE;

		switch (tempColor) {
		case BLUE:
			return COLOR.GREEN;
		case GREEN:
			return COLOR.WHITE;
		case WHITE:
			return COLOR.BROWN;
		case BROWN:
			return COLOR.BROWN_SUGAR;
		case BROWN_SUGAR:
			return COLOR.BLUE;
		}

		// should never occurs
		return null;
	}

	@Override
	public default boolean isValidGreen(int green) {
		return 260 - COLOR.values().length * 10 <= green;
	}

	@Override
	public default boolean isValidBlue(int blue) {
		return 0 <= blue && blue <= 10;
	}

}
